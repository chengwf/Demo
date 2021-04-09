package com.chengwf.utils.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chengwf.utils.R
import com.google.android.material.appbar.AppBarLayout

/**
 * 设置View圆角
 **/
fun View.setCircularCorner(
    cornerRadius: Float = 20f,
    @ColorRes colorRes: Int = R.color.colorPrimary
) {
    background = GradientDrawable().apply {
        setColor(context.getColor(colorRes))
        shape = GradientDrawable.RECTANGLE
        this.cornerRadius = cornerRadius
    }
}

fun View.circle(@ColorRes colorRes: Int = R.color.colorPrimary) {
    background = GradientDrawable().apply {
        shape = GradientDrawable.OVAL
        setColor(context.getColor(colorRes))
    }
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
 * view是否显示
 *
 * @param isVisible true显示，false隐藏，隐藏只是不显示，还在原来的位置上
 */
fun View.isVisible(isVisible: Boolean) = if (isVisible) {
    visible()
} else {
    invisible()
}


/**
 * @param isGone true隐藏，false显示，隐藏是完全隐藏，位置发生变动，被依赖的控件也会发生位移
 */
fun View.isGone(isGone: Boolean) = if (isGone) {
    gone()
} else {
    visible()
}

fun View.isGone() = visibility == View.GONE

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

/**
 * 全屏时挖孔屏的适配，设置本身高度与挖孔屏的高度相加
 */
fun AppBarLayout.diggingScreen() {
    (this.layoutParams as CoordinatorLayout.LayoutParams).height =
        this.layoutParams.height + getStatusBarHeight()
}

/**
 * 全屏时挖孔屏的适配，设置上边的间距为安全高度
 */
fun Toolbar.diggingScreen() {
    (layoutParams as AppBarLayout.LayoutParams).topMargin = getStatusBarHeight()
}


fun View.backgroundByGlide(url: String) {
    Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
            // 暂时还不知道这个是干啥用的
        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            background = BitmapDrawable(resources, bitmap)
        }
    })
}

/**
 * 检测view是否可见（是否被滚动到屏幕外）
 */
fun View.checkVisible(): Boolean {

    val rect = Rect(0, 0, Resources.getSystem().displayMetrics.widthPixels, measuredHeight)

    getLocationInWindow(IntArray(2))

    return getLocalVisibleRect(rect)
}