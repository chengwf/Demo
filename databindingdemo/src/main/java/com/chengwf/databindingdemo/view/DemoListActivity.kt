package com.chengwf.databindingdemo.view

import android.view.View
import com.chengwf.databindingdemo.R
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.ext.diggingScreen
import com.chengwf.utils.ext.launchActivity
import kotlinx.android.synthetic.main.activity_demo_list.*

class DemoListActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_demo_list

    override fun initViews() {
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { finish() }
    }

    fun toField(v: View) {
        launchActivity<BindFieldActivity>()
    }

    fun toList(v: View) {
        launchActivity<ListActivity>()
    }
}
