package com.chengwf.utils.ext

import android.graphics.Bitmap
import android.graphics.Matrix

/**
 * 旋转bitmap
 *
 * @param alpha 旋转的角度，正负皆可（360°）
 */
fun Bitmap.rotate(alpha: Float): Bitmap {
    // 围绕原地进行旋转
    val newBM = Bitmap.createBitmap(this, 0, 0, width, height, Matrix().apply { setRotate(alpha) }, false)
    if (newBM == this) {
        return newBM
    }
    return newBM
}