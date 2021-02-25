package com.chengwf.utils.ext

import android.widget.TextView

fun TextView.string() = this.text.toString()
fun TextView.int() = this.string().run {

    when {
        string().isEmpty() -> 0
        contains(".") -> toFloat().toInt()
        else -> toInt()
    }
}

fun TextView.float() = this.string().toFloat()