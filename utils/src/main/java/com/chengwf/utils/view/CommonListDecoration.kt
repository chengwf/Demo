package com.chengwf.utils.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 *
 * Created by kyleduo
 * @see [https://github.com/kyleduo/ExamplesFromMyBlog/tree/master/AlipayHome/app/src/main/java/com/kyleduo/alipayhome/widgets]
 */
class CommonListDecoration : ItemDecoration {
    private var mSpaceV = 0
    private var mSpaceH = 0

    constructor()
    constructor(spaceV: Int, spaceH: Int) {
        mSpaceV = spaceV
        mSpaceH = spaceH
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        if (mSpaceH == 0 && mSpaceV == 0) {
            val density =
                parent.context.resources.displayMetrics.density
            mSpaceH = (density * 16).toInt()
            mSpaceV = (density * 12).toInt()
        }
        var top = 0
        var left = 0
        var right = 0
        var bottom = 0
        when (parent.getChildLayoutPosition(view)) {
            0 -> {
                top = mSpaceV
                bottom = mSpaceV / 2
            }
            itemCount - 1 -> {
                top = mSpaceV / 2
                bottom = mSpaceV
            }
            else -> {
                top = mSpaceV / 2
                bottom = mSpaceV / 2
            }
        }
        left = mSpaceH
        right = mSpaceH
        outRect[left, top, right] = bottom
    }
}