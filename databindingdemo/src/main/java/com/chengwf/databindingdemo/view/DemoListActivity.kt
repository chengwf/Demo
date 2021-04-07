package com.chengwf.databindingdemo.view

import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class DemoListActivity : BaseDemoListActivity() {


    override fun getAdapterList() = arrayOf("字段，非列表", "RecyclerView", "DiffUtil").toMutableList()

    override fun getActivityTitle() = "DataBinding相关"

    override fun onClickChild(position: Int) {
        when (position) {
            0 -> launchActivity<BindFieldActivity>()
            1 -> launchActivity<ListActivity>()
            2 -> launchActivity<DiffUtilActivity>()
            else -> {
            }
        }
    }
}
