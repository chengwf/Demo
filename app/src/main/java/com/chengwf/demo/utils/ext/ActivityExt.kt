package com.chengwf.demo.utils.ext

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.Display

/**
 * 下面两个跳转activity的方法，一个是结束自己一个不结束自己
 * ，省略掉this参数，kotlin写this太长了，而且有时候编译器还不提示的。。。
 */
fun Activity?.launchActivity(cls: Class<out Activity>) {
    this?.let { it.startActivity(Intent(it, cls)) }
}

fun Activity?.tohActivity(cls: Class<out Activity>) {
    this?.let {
        it.startActivity(Intent(it, cls))
        it.onBackPressed()
    }
}


/**
 * 取得虚拟导航栏高度
 */
fun Activity.getNavigationBarHeight(): Int {
    val display: Display = this.windowManager.defaultDisplay
    val size = Point()
    val realSize = Point()
    display.getSize(size)
    display.getRealSize(realSize)
    val resourceId: Int = Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
    val height: Int = Resources.getSystem().getDimensionPixelSize(resourceId)
    //超出系统默认的导航栏高度以上，则认为存在虚拟导航
    return if (realSize.y - size.y > height - 10) {
        height
    } else 0
}


/**
 * 是否存在刘海屏
 */
fun Activity.isCutoutDisplay(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val windowInsets = window?.decorView?.rootWindowInsets?.displayCutout
        windowInsets != null
    } else
        false
}

/**
 * 取得刘海高度
 */
fun Activity.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")

    if (resourceId > 0) {
        result = Resources.getSystem().getDimensionPixelSize(resourceId)
    }
    return result
}
