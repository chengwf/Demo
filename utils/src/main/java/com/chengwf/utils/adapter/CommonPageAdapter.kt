package com.chengwf.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CommonPageAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentNameList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentNameList = ArrayList<String>()


    fun addFragment(fragment: Fragment, title: String = fragment::class.java.simpleName) {
        fragmentList.add(fragment)
        fragmentNameList.add(title)
    }
}