package com.chengwf.demo.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPage : ViewPager {
    fun setScroll(scroll: Boolean) {
        isScroll = scroll
    }

    /**
     * 默认不可滑动
     */
    private var isScroll = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context) : super(context) {}

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isScroll && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isScroll && super.onInterceptTouchEvent(ev)
    }
}