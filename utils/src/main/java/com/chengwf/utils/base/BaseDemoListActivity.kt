package com.chengwf.utils.base

import androidx.recyclerview.widget.LinearLayoutManager
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.R
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_base_demo_list.*
import java.util.ArrayList

abstract class BaseDemoListActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_base_demo_list
    override fun initViews() {
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { onBackPressed() }

        id_toolbar.title = getActivityTitle()
        id_recycler_view.layoutManager = LinearLayoutManager(this)
        id_recycler_view.adapter = BaseDemoListAdapter(getAdapterList()).apply {
            setOnItemClickListener { _, _, position -> onClickChild(position) }
        }
        id_recycler_view.setHasFixedSize(true)
    }

    protected open fun onClickChild(position: Int) {}

    abstract fun getAdapterList(): ArrayList<String>

    abstract fun getActivityTitle(): String
}