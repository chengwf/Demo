package com.chengwf.animationdemo

import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class AnimatorDemoListActivity : BaseDemoListActivity() {
    override fun getAdapterList() =
        resources.getStringArray(R.array.animator_demo_list).toMutableList()

    override fun getActivityTitle() = "动画相关Demo"

    override fun onClickChild(position: Int) {
        when (position) {
            0 -> launchActivity<DynamicAnimationActivity>()
        }

    }
}