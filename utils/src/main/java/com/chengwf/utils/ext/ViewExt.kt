package com.chengwf.utils.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout

// 设置View圆角
fun View.setCircularCorner(color: Int = Color.WHITE, cornerRadius: Float = 20f) {
    val gradientDrawable = GradientDrawable()
    gradientDrawable.setColor(color)
    gradientDrawable.cornerRadius = cornerRadius
    background = gradientDrawable
}

// 隐藏键盘
fun View.hideSoftInput() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * @param isGone true隐藏，false显示，隐藏是完全隐藏，位置发生变动，被依赖的控件也会发生位移
 */
fun View.setGone(isGone: Boolean) = if (isGone) {
    gone()
} else {
    visible()
}

/**
 * view是否显示
 *
 * @param isVisible true显示，false隐藏，隐藏只是不显示，还在原来的位置上
 */
fun View.setVisible(isVisible: Boolean) = if (isVisible) {
    visible()
} else {
    invisible()
}

fun EditText.string() = this.text.toString()

/**
 * 全屏的情况
 */
fun AppBarLayout.topMargin() {
    (layoutParams as AppBarLayout.LayoutParams).topMargin = getStatusBarHeight()
}

/**
 * 全屏的情况
 */
fun Toolbar.setHeight() {
    (this.layoutParams as CoordinatorLayout.LayoutParams).apply {
        height += getStatusBarHeight()
    }
}

/**
 * 状态栏的安全高度
 */
fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")

    if (resourceId > 0) {
        result = Resources.getSystem().getDimensionPixelSize(resourceId)
    }
    return result
}

fun View.backgraundByGlide(url: String) {
    Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
            // 暂时还不知道这个是干啥用的
        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            background = BitmapDrawable(resources, bitmap)
        }
    })
}