package com.chengwf.utils.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chengwf.utils.R

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


fun ImageView.loadCircleImg(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)

fun ImageView.loadCircleImg(@DrawableRes resId: Int) =
        Glide.with(this).load(resId)
                .apply(RequestOptions.circleCropTransform())
                .into(this)