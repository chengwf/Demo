package com.chengwf.utils.ext


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.Display
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * 下面两个跳转activity的方法，一个是结束自己一个不结束自己
 * ，省略掉this参数，kotlin写this太长了，而且有时候编译器还不提示的。。。
 *
 * @param block 在这里携带intent参数
 */
inline fun <reified T> Fragment.launchActivity(block: Intent.() -> Unit = {}) {
    activity?.let { it.startActivity(Intent(it, T::class.java).apply { block() }) }
}

/***
 * 隐式启动
 */
fun Fragment.launchActivity(action: String, block: Intent.() -> Unit = {}) {
    startActivity(Intent().apply {
        setAction(action)
        block()
    })
}

inline fun <reified T> Fragment.tohActivity(block: Intent.() -> Unit = {}) {
    activity?.let {
        it.startActivity(Intent(it, T::class.java).apply { block() })
        it.onBackPressed()
    }
}

/**
 * 取得虚拟导航栏高度
 */
fun Fragment.getNavigationBarHeight(): Int {


    var nvaHeight = 0

    activity?.let {
        val display: Display = it.windowManager.defaultDisplay

        val size = Point()
        val realSize = Point()
        display.getSize(size)
        display.getRealSize(realSize)
        val resourceId: Int =
            Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
        val height: Int = Resources.getSystem().getDimensionPixelSize(resourceId)
        //超出系统默认的导航栏高度以上，则认为存在虚拟导航
        nvaHeight = if (realSize.y - size.y > height - 10) {
            height
        } else 0
    }
    return nvaHeight
}

fun Fragment.isCutoutDisplay(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val windowInsets = activity?.window?.decorView?.rootWindowInsets?.displayCutout
        windowInsets != null
    } else
        false
}

fun Fragment.getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")

    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * 隐藏输入法
 */
fun Fragment.hindKeyBroad() {
    activity?.let {
        val inputMethodManager =
            it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            it.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}


fun Fragment.log(message: String) = Log.d("TAG_${this.javaClass.simpleName}", message)