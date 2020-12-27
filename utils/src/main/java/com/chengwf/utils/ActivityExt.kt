package com.chengwf.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Display
import android.view.inputmethod.InputMethodManager

/**
 * 取得虚拟导航栏高度
 */
fun Activity.getNavigationBarHeight(): Int {
    val display: Display = this.windowManager.defaultDisplay
    val size = Point()
    val realSize = Point()
    display.getSize(size)
    display.getRealSize(realSize)
    val resourceId: Int =
        Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
    val height: Int = Resources.getSystem().getDimensionPixelSize(resourceId)
    //超出系统默认的导航栏高度以上，则认为存在虚拟导航
    return if (realSize.y - size.y > height - 10) {
        height
    } else 0
}

fun Activity.hideKeyboard(token: IBinder?): Boolean {
    if (token != null) {
        val im: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}


/**
 * 隐藏输入法
 */
fun Activity.hindKeyBroad() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}


/**
 * 下面两个跳转activity的方法，一个是结束自己一个不结束自己
 * ，省略掉this参数，kotlin写this太长了，而且有时候编译器还不提示的。。。
 */
inline fun <reified T> Activity.launchActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply { block() })
}

inline fun <reified T> Activity.tohActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply { block() })
    finish()
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

fun Activity.log(message: String) = Log.d("TAG_${this.javaClass.simpleName}", message)
