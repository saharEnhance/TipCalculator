package com.example.tipcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    var tip: Tip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBad.setOnClickListener { calculateTip(BAD_TIP_PERCENTAGE) }
        buttonOkay.setOnClickListener { calculateTip(OKAY_TIP_PERCENTAGE) }
        buttonGreat.setOnClickListener { calculateTip(GREAT_TIP_PERCENTAGE) }
        buttonPay.setOnClickListener {
            tip?.let {
                val intent = Intent(this, TotalActivity::class.java)
                intent.putExtra("tip", tip)
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("tip", tip)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("tip")) {
            this.tip = savedInstanceState.getParcelable("tip")!!
            this.tip?.let {
                showResult(it.tip, it.total, it.percent)
            }
        }
    }

    private fun calculateTip(percentage: Double) {

        inputAmount.text?.toString()?.let { bill ->
            if (bill.isNotEmpty()) {
                val billTotal = bill.toDouble()
                var tip = billTotal * percentage
                if (switchRound.isChecked) {
                    val additionalTip = ceil(tip + billTotal) - (tip + billTotal)
                    tip += additionalTip
                }
                showResult(tip, tip + billTotal, tip / billTotal * 100)
            } else {
                Toast.makeText(applicationContext, "Set an amount before calculating the tip", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showResult(tip: Double, total: Double, percentage: Double) {
        textTip.text = String.format("%.2f", tip)
        textTotal.text = String.format("%.2f", total)
        textPercent.text = String.format("%.2f", percentage) + "%"
        this.tip = Tip(tip, percentage, total)
    }

    companion object {
        const val BAD_TIP_PERCENTAGE = 0.10
        const val OKAY_TIP_PERCENTAGE = 0.15
        const val GREAT_TIP_PERCENTAGE = 0.20
    }
}