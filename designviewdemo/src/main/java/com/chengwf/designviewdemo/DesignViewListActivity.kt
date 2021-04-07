package com.chengwf.designviewdemo

import com.chengwf.designviewdemo.other.TabLayoutDemoActivity
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class DesignViewListActivity : BaseDemoListActivity() {
    override fun getActivityTitle() = "DesignView相关"

    override fun getAdapterList(): ArrayList<String> {
        val list = ArrayList<String>()
        resources.getStringArray(R.array.DesignViewList).forEach { list.add(it) }
        return list
    }

    override fun onClickChild(position: Int) {
        launchActivity<TabLayoutDemoActivity>()
    }
}
