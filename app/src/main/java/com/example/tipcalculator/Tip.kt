package com.example.tipcalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tip(val tip: Double, val percent: Double, val total: Double) : Parcelable