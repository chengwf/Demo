package com.chengwf.demo

import com.chengwf.customview.LotteryPanListActivity
import com.chengwf.databindingdemo.view.DemoListActivity
import com.chengwf.demo.animation.DynamicAnimationActivity
import com.chengwf.demo.dialog.CustomDialogActivity
import com.chengwf.demo.recyclerview.SpanSizeActivity
import com.chengwf.demo.snack_bar.TopSnackBarActivity
import com.chengwf.demo.viewchat.MoreChatActivity
import com.chengwf.designviewdemo.DesignViewListActivity
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity
import com.coder.zzq.smartshow.toast.SmartToast

class MainActivity : BaseDemoListActivity() {

    override fun getAdapterList() = resources.getStringArray(R.array.menu_main).toMutableList()

    override fun getActivityTitle() = "Main"

    override fun onClickChild(position: Int) {
        when (position) {
            0 -> launchActivity<MoreChatActivity>()
            1 -> launchActivity<TopSnackBarActivity>()
            2 -> launchActivity<SpanSizeActivity>()
            3 -> launchActivity<CustomDialogActivity>()
            4 -> SmartToast.show("还没做~~")
            5 -> launchActivity<DynamicAnimationActivity>()
            6 -> launchActivity<LotteryPanListActivity>()
            7 -> launchActivity<DemoListActivity>()
            8 -> launchActivity<com.chengwf.recyclerviewdemo.DemoListActivity>()
            9 -> launchActivity<DesignViewListActivity>()
        }
    }
}