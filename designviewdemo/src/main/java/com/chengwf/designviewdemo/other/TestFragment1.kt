package com.chengwf.designviewdemo.other

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengwf.designviewdemo.R
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_tab_test_1.*

class TestFragment1 : BaseFragment() {
    override fun getLayoutId() =
        R.layout.fragment_tab_test_1

    companion object {
        val instant by lazy { TestFragment1() }
    }

    private var isAppbarExpanded = true

    /**
     * 是否启用下拉刷新
     */
    fun isRefresh(): Boolean {
        return isAppbarExpanded
    }

    override fun initView() {
        recycler_view_3.layoutManager =
            GridLayoutManager(recycler_view_3.context, 1, GridLayoutManager.HORIZONTAL, false)
        recycler_view_3.adapter = DemoListAdapter3(
            buildTestData(8)
        )
        recycler_view_3.setHasFixedSize(true)


        recycler_view_head.layoutManager = GridLayoutManager(id_recycler_view.context, 3)
        recycler_view_head.adapter = BaseDemoListAdapter(buildTestData(5))
        recycler_view_head.setHasFixedSize(true)

        val layoutManager1 = LinearLayoutManager(id_recycler_view.context)
        id_recycler_view.layoutManager = layoutManager1
        id_recycler_view.adapter = BaseDemoListAdapter(buildTestData(30))
        id_recycler_view.setHasFixedSize(true)
        // 监听appbar是否完全展开
        id_appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            isAppbarExpanded = verticalOffset == 0
            TabLayoutDemoActivity.RefreshEnable(isAppbarExpanded).post()
        })
    }


    private fun buildTestData(count: Int): ArrayList<String> {
        val list = ArrayList<String>()
        (0..count).forEach {
            list.add("- 第 ${it + 1} 项 -")
        }
        return list
    }
}