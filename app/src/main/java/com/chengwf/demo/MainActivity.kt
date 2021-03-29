package com.chengwf.demo

import com.chengwf.customview.LotteryPanListActivity
import com.chengwf.databindingdemo.view.DemoListActivity
import com.chengwf.demo.animation.DynamicAnimationActivity
import com.chengwf.demo.base.BaseActivity
import com.chengwf.demo.dialog.CustomDialogActivity
import com.chengwf.demo.recyclerview.SpanSizeActivity
import com.chengwf.demo.snack_bar.TopSnackBarActivity
import com.chengwf.demo.viewchat.MoreChatActivity
import com.chengwf.utils.ext.launchActivity
import com.coder.zzq.smartshow.toast.SmartToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initView() {

        id_recycler_view.adapter =
            MainMenuAdapter(resources.getStringArray(R.array.menu_main).toMutableList()).apply {
                setOnItemClickListener { _, _, position ->
                    when (position) {
                        0 -> launchActivity<MoreChatActivity>()
                        1 -> launchActivity<TopSnackBarActivity>()
                        2 -> launchActivity<SpanSizeActivity>()
                        3 -> launchActivity<CustomDialogActivity>()
                        4 -> SmartToast.show("还没做~~")
                        5 -> launchActivity<DynamicAnimationActivity>()
                        6 -> launchActivity<LotteryPanListActivity>()
                        7 -> launchActivity<DemoListActivity>()
                    }
                }
            }
    }

    override fun getLayoutResId() = R.layout.activity_main
}