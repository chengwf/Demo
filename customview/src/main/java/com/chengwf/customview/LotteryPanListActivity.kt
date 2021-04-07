package com.chengwf.customview

import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class LotteryPanListActivity : BaseDemoListActivity() {

    override fun onClickChild(position: Int) {
        when (position) {
            0 -> launchActivity<TurntableActivity>()
            2 -> launchActivity<PieViewActivity>()
            else -> {
            }
        }
    }

    override fun getAdapterList() = arrayOf("用SurfaceView实现（未完成）", "Canvas实现，可指定目标").toMutableList()

    override fun getActivityTitle() = "转盘相关View"
}
