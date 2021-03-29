package com.chengwf.utils.ext

import android.content.res.Resources

/**
 * 状态栏的安全高度
 */
fun Any.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")

    if (resourceId > 0) {
        result = Resources.getSystem().getDimensionPixelSize(resourceId)
    }
    return result
}