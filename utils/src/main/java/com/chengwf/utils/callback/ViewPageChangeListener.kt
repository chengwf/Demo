package com.chengwf.utils.callback

import androidx.viewpager.widget.ViewPager

class ViewPageChangeListener(

//    private val onSelected: (position: Int) -> Unit = {},
//    private val onScrolled: (position: Int, offset: Float, offsetPixels: Int) -> Unit = { _, _, _ -> },
//    private val onStateChanged: (state: Int) -> Unit = {}

    private val func: ViewPageChangeListener.() -> Unit = {}

) : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
        onStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        onSelected(position)
    }

    var onSelected: (position: Int) -> Unit = {}
    var onScrolled: (position: Int, offset: Float, offsetPixels: Int) -> Unit = { _, _, _ -> }
    var onStateChanged: (state: Int) -> Unit = {}
}