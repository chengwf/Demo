package com.chengwf.utils

import android.content.res.Resources
import android.util.TypedValue


fun Int.toPix() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(), Resources.getSystem().displayMetrics)