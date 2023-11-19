package com.example.taskhotelapp.presentation

import android.content.Context
import com.example.presentation.R

object UiUtil {
    fun getNumberText(context: Context, number:Int):String{
        val numbers = listOf<String>(
            context.getString(R.string.fisrt),
            context.getString(R.string.second),
            context.getString(R.string.third),
            context.getString(R.string.fourth),
            context.getString(R.string.fifth),
            context.getString(R.string.sixth),
            context.getString(R.string.seventh),
            context.getString(R.string.eighth),
            context.getString(R.string.ninght)
        )
        return if(number<=numbers.size && number>0) numbers[number-1] else (number.toString() + context.getString(R.string.number_ending))
    }
}