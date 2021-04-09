package com.chengwf.utils.base

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengwf.utils.Const
import com.chengwf.utils.R
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.ext.diggingScreen
import com.chengwf.utils.view.CommonListDecoration
import kotlinx.android.synthetic.main.activity_base_demo_list.*

abstract class BaseDemoListActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_base_demo_list
    override fun initViews() {
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { onBackPressed() }
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
        id_toolbar.title = getActivityTitle()
        id_recycler_view.layoutManager = LinearLayoutManager(this)
        id_recycler_view.adapter = BaseDemoListAdapter(getAdapterList()).apply {
            setOnItemClickListener { adapter, view, position ->
                onClickChild(
                    position,
                    view,
                    adapter.data[position] as String
                )
            }
        }
        id_recycler_view.addItemDecoration(CommonListDecoration())
        id_recycler_view.setHasFixedSize(true)
    }

    protected open fun onClickChild(position: Int, view: View, item: String) {}

    abstract fun getAdapterList(): MutableList<String>

    abstract fun getActivityTitle(): String
}