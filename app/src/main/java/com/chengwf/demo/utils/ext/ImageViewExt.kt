package com.chengwf.demo.utils.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.chengwf.demo.R


/**
 * 最简单的
 */
fun ImageView.loadUrl1(url: String) = Glide.with(this.context).load(url).into(this)

/**
 * imageView加载网络图片
 *
 * @param url 图片的url,必要参数
 * @param placeImg 加载图片时显示的占位图: 默认app的icon
 * @param errorImg 加载失败时显示的占位图: 默认app的icon
 * @param requestOptions 圆角啦图片显示大小啦之类的需求：默认RequestOptions()
 * @param diskCacheStrategy 缓存策略：默认NONE
 */
fun ImageView.loadUrl(
    url: String?
    , placeImg: Int = R.mipmap.ic_launcher_round
    , errorImg: Int = R.mipmap.ic_launcher_round
    , requestOptions: BaseRequestOptions<*> = RequestOptions()
    , diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
) {
    Glide.with(this).load(url)
        .apply(requestOptions)
        .placeholder(placeImg)
        .diskCacheStrategy(diskCacheStrategy)
        .error(errorImg)
        .into(this)
}

/**
 * 没有错误占位图
 */
fun ImageView.loadUrl2(
    url: String?
    , errorImg: Int = R.mipmap.ic_launcher_round
    , requestOptions: BaseRequestOptions<*> = RequestOptions()
    , diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
) {
    Glide.with(this).load(url)
        .apply(requestOptions)
        .diskCacheStrategy(diskCacheStrategy)
        .error(errorImg)
        .into(this)
}

/**
 * 没有加载占位图
 */
fun ImageView.loadUrl3(
    url: String?
    , placeImg: Int = R.mipmap.ic_launcher_round
    , requestOptions: BaseRequestOptions<*> = RequestOptions()
    , diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
) {
    Glide.with(this).load(url)
        .apply(requestOptions)
        .diskCacheStrategy(diskCacheStrategy)
        .placeholder(placeImg)
        .into(this)
}

/**
 * 没有加载和错误占位图
 */
fun ImageView.loadUrl4(
    url: String?
    , requestOptions: BaseRequestOptions<*> = RequestOptions()
    , diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
) {
    Glide.with(this).load(url)
        .apply(requestOptions)
        .diskCacheStrategy(diskCacheStrategy)
        .into(this)
}

/**
 * 加载资源文件的图片，不存在卡顿的情况吧
 */
fun ImageView.loadRes(
    @DrawableRes resId: Int
    , requestOptions: BaseRequestOptions<*> = RequestOptions()
    , diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
) {
    Glide.with(this).load(resId)
        .apply(requestOptions)
        .diskCacheStrategy(diskCacheStrategy)
        .into(this)
}


