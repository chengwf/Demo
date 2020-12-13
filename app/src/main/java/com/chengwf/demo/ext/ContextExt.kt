package com.chengwf.demo.ext

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment


fun Activity?.launchActivity(cls: Class<out Activity>) {
    this?.let { it.startActivity(Intent(it, cls)) }
}

fun Activity?.tohActivity(cls: Class<out Activity>) {
    this?.let {
        it.startActivity(Intent(it, cls))
        it.onBackPressed()
    }
}

fun Fragment.launchActivity(cls: Class<out Activity>) {
    activity?.let { it.startActivity(Intent(it, cls)) }
}

fun Fragment.tohActivity(cls: Class<out Activity>) {
    activity?.let {
        it.startActivity(Intent(it, cls))
        it.onBackPressed()
    }
}