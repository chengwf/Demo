package com.chengwf.demo.viewchat.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val mViewList: ArrayList<View>) :
    PagerAdapter() {


    override fun getPageTitle(position: Int): CharSequence? {
        return "title $position"
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mViewList[position]);
        return mViewList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViewList[position])
    }
}