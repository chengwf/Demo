package com.chengwf.utils.ext

import android.content.res.Resources
import android.util.TypedValue
import java.util.concurrent.TimeUnit


fun Int.toPix() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)