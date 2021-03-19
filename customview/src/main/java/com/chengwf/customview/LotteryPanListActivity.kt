package com.chengwf.customview

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.ext.getStatusBarHeight
import com.chengwf.utils.ext.launchActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_lottery_pan_list.*
import kotlinx.android.synthetic.main.activity_lottery_pan_list.id_appbar_layout
import kotlinx.android.synthetic.main.activity_lottery_pan_list.id_toolbar

class LotteryPanListActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_lottery_pan_list

    override fun initViews() {
        id_toolbar.setNavigationOnClickListener { finish() }
        (id_appbar_layout.layoutParams as CoordinatorLayout.LayoutParams).height =
            id_appbar_layout.layoutParams.height + getStatusBarHeight()

        (id_toolbar.layoutParams as AppBarLayout.LayoutParams).topMargin = getStatusBarHeight()

        id_tool_1.setOnClickListener { launchActivity<TurntableActivity>() }
        id_tool_2.setOnClickListener { launchActivity<PieViewActivity>() }
    }
}
