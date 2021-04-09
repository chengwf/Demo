package com.chengwf.demo

import android.view.View
import androidx.core.util.Pair
import com.chengwf.animationdemo.AnimatorDemoListActivity
import com.chengwf.customview.LotteryPanListActivity
import com.chengwf.databindingdemo.view.DemoListActivity
import com.chengwf.demo.dialog.CustomDialogActivity
import com.chengwf.demo.recyclerview.SpanSizeActivity
import com.chengwf.demo.snack_bar.TopSnackBarActivity
import com.chengwf.demo.viewchat.MoreChatActivity
import com.chengwf.designviewdemo.DesignViewListActivity
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class MainActivity : BaseDemoListActivity() {

    override fun getAdapterList() = resources.getStringArray(R.array.menu_main).toMutableList()

    override fun getActivityTitle() = "Main"

    override fun onClickChild(position: Int, view: View, item: String) {

        val pair: Pair<View, String> = Pair(
            view.findViewById(R.id.id_text_view),
            Const.TRANSITION_NAME_TITLE
        )
        when (position) {
            0 -> launchActivity<MoreChatActivity>(pair)
            1 -> launchActivity<TopSnackBarActivity>(pair)
            2 -> launchActivity<SpanSizeActivity>(pair)
            3 -> launchActivity<CustomDialogActivity>(pair)
            4 -> launchActivity<AnimatorDemoListActivity>(pair)
            5 -> launchActivity<LotteryPanListActivity>(pair)
            6 -> launchActivity<DemoListActivity>(pair)
            7 -> launchActivity<com.chengwf.recyclerviewdemo.DemoListActivity>(pair)
            8 -> launchActivity<DesignViewListActivity>(pair)
        }
    }
}