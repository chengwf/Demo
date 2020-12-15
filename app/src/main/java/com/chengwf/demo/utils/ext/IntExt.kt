package com.chengwf.demo.utils.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 * 将int类型的db单位转成像素点
 */
fun Int.toPix() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(), Resources.getSystem().displayMetrics)