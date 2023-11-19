package com.example.common.taskhotelapp.util

import java.text.DecimalFormat

fun String.toPriceFormat():String{
    val m: Double = this.toDouble()
    val formatter = DecimalFormat("###,###,###")
    return formatter.format(m)
}