package com.chengwf.utils.ext

import android.text.format.DateFormat
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 目标时间到现在的距离
 */
fun Long.theRangeToNow(): CharSequence {
    return DateUtils.getRelativeTimeSpanString(this)
}
/**
 * 目标时间到指定时间戳的距离
 * @param lastTime 某个时间点的时间戳
 */
fun Long.theRangeToMe(lastTime: Long): CharSequence {
    return DateUtils.getRelativeTimeSpanString(this, lastTime, DateUtils.SECOND_IN_MILLIS)
}


fun Long.toNow(): CharSequence {
    return DateFormat.format("HH:mm:ss", this)
}

/**
 * 将秒钟单位long值转成毫秒
 */
fun Long.second() = TimeUnit.SECONDS.toMillis(this)

/**
 * 将分钟单位long值转成毫秒
 */
fun Long.minute() = TimeUnit.MINUTES.toMillis(this)

/**
 * 将小时单位long值转成毫秒
 */
fun Long.hour() = TimeUnit.HOURS.toMillis(this)

/**
 * 将天单位long值转成毫秒
 */
fun Long.day() = TimeUnit.DAYS.toMillis(this)


fun Long.toSeconds() = TimeUnit.MILLISECONDS.toSeconds(this)
fun Long.toMinutes() = TimeUnit.MILLISECONDS.toMinutes(this)

fun Long.toHours() = TimeUnit.MILLISECONDS.toHours(this)

fun Long.toDays() = TimeUnit.MILLISECONDS.toDays(this)

fun Long.formatTiming(): String {

    return SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        .apply { timeZone = TimeZone.getTimeZone("GMT+00:00") }
        .format(this)
}