package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_total.*

class TotalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        val tip: Tip? = intent.getParcelableExtra("tip")
        tip?.let {
            textTotal.text = it.total.toString()
        }
    }
}
