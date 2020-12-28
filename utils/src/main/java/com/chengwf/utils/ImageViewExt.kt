package com.chengwf.utils

import android.widget.ImageView
import androidx.annotation.Size
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 加载网页图片，view.方法，传入url即可
 */
fun ImageView.loadUrl(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)

fun ImageView.loadUrl5(url: String?) =
        Glide.with(this).load(url)
                .centerCrop()
                .into(this)

/**
 * 多了个error
 */
fun ImageView.loadUrl2(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions().centerCrop())
                .into(this)

fun ImageView.loadUrl3(url: String, @Size(min = 1) imgWidth: Float, @Size(min = 1) imgHeight: Float) =
        Glide.with(this.context).load(url)
                .apply(
                        RequestOptions()
                                .dontAnimate()
                                .override(imgWidth.toInt(), imgHeight.toInt())
                )
                .into(this)

/**
 * 多了个error
 */
fun ImageView.loadUrl4(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)

fun ImageView.loadCircleImgByUrl(url: String?) =
        Glide.with(this).load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)

fun ImageView.loadCircleImgByRes(resId: Int) =
        Glide.with(this).load(resId)
                .apply(RequestOptions.circleCropTransform())
                .into(this)