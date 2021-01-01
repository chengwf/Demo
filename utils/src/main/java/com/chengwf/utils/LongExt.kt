package com.chengwf.utils

import android.text.format.DateUtils

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