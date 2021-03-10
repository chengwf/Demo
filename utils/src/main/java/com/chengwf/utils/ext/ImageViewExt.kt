package com.chengwf.utils.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 多了个error
 */
fun ImageView.load(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions().centerCrop())
                .into(this)

fun ImageView.load(@DrawableRes resId: Int) =
        Glide.with(this).load(resId)
                .apply(RequestOptions().centerCrop())
                .into(this)


fun ImageView.loadCircle(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)

fun ImageView.loadCircle(@DrawableRes resId: Int) =
        Glide.with(this).load(resId)
                .apply(RequestOptions.circleCropTransform())
                .into(this)