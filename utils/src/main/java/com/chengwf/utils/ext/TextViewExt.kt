package com.chengwf.utils.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chengwf.utils.R
import kotlin.math.min

fun TextView.string() = this.text.toString()
fun TextView.int() = this.string().run {

    when {
        string().isEmpty() -> 0
        contains(".") -> toFloat().toInt()
        else -> toInt()
    }
}

fun TextView.float() = this.string().toFloat()

/**
 * 设置颜色直接使用colors.xml中定义的颜色即可
 */
fun TextView.setColor(@ColorRes resId: Int) {
    this.setTextColor(ContextCompat.getColor(context, resId))
}

fun TextView.setColor(@Size(min = 4) colorStr: String) {
    this.setTextColor(Color.parseColor(colorStr))
}

fun TextView.drawableTop(@DrawableRes resId: Int) {

    ContextCompat.getDrawable(context, resId)?.let {
        it.setBounds(0, 0, it.minimumWidth, it.minimumHeight)
        this.setCompoundDrawables(null, it, null, null)
    }
}

fun TextView.drawableStart(@DrawableRes resId: Int) {

    ContextCompat.getDrawable(context, resId)?.let {
        it.setBounds(0, 0, it.minimumWidth, it.minimumHeight)
        this.setCompoundDrawables(it, null, null, null)
    }
}

fun TextView.drawableEnd(@DrawableRes resId: Int) {

    ContextCompat.getDrawable(context, resId)?.let {
        it.setBounds(0, 0, it.minimumWidth, it.minimumHeight)
        this.setCompoundDrawables(null, null, it, null)
    }
}

fun TextView.drawableEnd(url: String) {

    urlToBitmap(context, url) { bitmap ->
        val drawable = BitmapDrawable(resources, bitmap).apply {
            setBounds(0, 0, minimumWidth, minimumHeight)
        }
        this@drawableEnd.setCompoundDrawables(null, null, drawable, null)
    }
}

fun TextView.drawableBottom(@DrawableRes resId: Int) {

    ContextCompat.getDrawable(context, resId)?.let {
        it.setBounds(0, 0, it.minimumWidth, it.minimumHeight)
        this.setCompoundDrawables(null, null, null, it)
    }
}

fun urlToBitmap(context: Context, url: String, func: (Bitmap) -> Unit) {
    Glide.with(context)
        .asBitmap()
        .circleCrop()
        .load(url)
//        .placeholder(R.mipmap.ic_launcher)
//        .error(R.mipmap.ic_launcher)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                func(bitmap)
            }
        })
}
