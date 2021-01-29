package com.chengwf.utils.ext

import android.content.res.Resources
import android.util.TypedValue
import java.util.concurrent.TimeUnit


fun Int.toPix() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

/**
 * 将秒钟单位long值转成毫秒
 */
fun Int.second() = TimeUnit.SECONDS.toMillis(this.toLong())

/**
 * 将分钟单位long值转成毫秒
 */
fun Int.minute() = TimeUnit.MINUTES.toMillis(this.toLong())

/**
 * 将小时单位long值转成毫秒
 */
fun Int.hour() = TimeUnit.HOURS.toMillis(this.toLong())

/**
 * 将天单位long值转成毫秒
 */
fun Int.day() = TimeUnit.DAYS.toMillis(this.toLong())