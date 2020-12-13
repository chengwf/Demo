package com.chengwf.demo

import com.chengwf.demo.base.BaseActivity
import com.chengwf.demo.dialog.CustomDialogActivity
import com.chengwf.demo.ext.launchActivity
import com.chengwf.demo.recyclerview.SpanSizeActivity
import com.chengwf.demo.snack_bar.TopSnackBarActivity
import com.chengwf.demo.viewchat.MoreChatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initView() {
        bn_chat_more.setOnClickListener { launchActivity(MoreChatActivity::class.java) }
        bn_top_snack.setOnClickListener { launchActivity(TopSnackBarActivity::class.java) }
        bn_span_size.setOnClickListener { launchActivity(SpanSizeActivity::class.java) }
        bn_custom_dialog.setOnClickListener { launchActivity(CustomDialogActivity::class.java) }
    }
    override fun getLayoutResId() = R.layout.activity_main
}