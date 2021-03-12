package com.chengwf.utils.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

/**
 * 多了个error
 */
fun ImageView.load(url: String?) =
        Glide.with(this).load(url)
                .centerCrop()
                .into(this)

fun ImageView.load(@DrawableRes resId: Int) =
        Glide.with(this).load(resId)
                .centerCrop()
                .into(this)


fun ImageView.loadCircle(url: String?) =
        Glide.with(this).load(url)
                .circleCrop()
                .into(this)

fun ImageView.loadCircle(@DrawableRes resId: Int) =
        Glide.with(this).load(resId)
                .circleCrop()
                .into(this)