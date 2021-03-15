package com.chengwf.utils.view

import android.graphics.Bitmap

data class CirclePanData(val userId: String, val url: String) {
    // 看选择吧，若不是用url，这个就不需要了
    // 不建议直接在这里转成bitmap
    var bitmap: Bitmap? = null
}