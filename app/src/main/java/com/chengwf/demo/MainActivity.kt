package com.chengwf.demo

import com.chengwf.demo.base.BaseActivity
import com.chengwf.demo.dialog.CustomDialogActivity
import com.chengwf.demo.recyclerview.SpanSizeActivity
import com.chengwf.demo.snack_bar.TopSnackBarActivity
import com.chengwf.demo.utils.ext.launchActivity
import com.chengwf.demo.viewchat.MoreChatActivity
import com.chengwf.utils.launchActivity
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
                    }
                }
            }
    }

    override fun getLayoutResId() = R.layout.activity_main
}