package com.chengwf.utils.view

import android.content.Context
import android.content.res.Resources
import android.graphics.PointF
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

/**
 * 控制滑动速度
 */
class ScrollSpeedLinearLayoutManger : LinearLayoutManager {
    constructor(context: Context?) : super(context)
    constructor(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun smoothScrollToPosition(
        recyclerView: RecyclerView?,
        state: RecyclerView.State?,
        position: Int
    ) {

        val linearSmoothScroller = object : LinearSmoothScroller(recyclerView?.context) {
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@ScrollSpeedLinearLayoutManger.computeScrollVectorForPosition(
                    targetPosition
                )
            }


            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                return (Resources.getSystem().displayMetrics.densityDpi * 0.6f) / Resources.getSystem().displayMetrics.densityDpi
            }
        }

        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }
}