package com.chengwf.designviewdemo.other

import android.graphics.Rect
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chengwf.designviewdemo.R
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.base.BaseFragment
import com.chengwf.utils.ext.log
import kotlinx.android.synthetic.main.fragment_tab_test_1.*
import org.greenrobot.eventbus.EventBus

class TestFragment1 : BaseFragment() {
    override fun getLayoutId() =
        R.layout.fragment_tab_test_1

    companion object {
        val instant by lazy { TestFragment1() }
    }

    /**
     * 是否启用下拉刷新
     */
    fun isRefresh(): Boolean {
        return (id_recycler_view.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0
                && recycler_view_3.getLocalVisibleRect(Rect())
    }

    override fun initView() {
//        id_button.setOnClickListener { SmartToast.show("fragment 1") }


        recycler_view_3.layoutManager = GridLayoutManager(recycler_view_3.context, 3)
        recycler_view_3.adapter = DemoListAdapter3(
            buildTestData(8)
        ).apply {
            addFooterView(LayoutInflater.from(context).inflate(R.layout.layout_foot, null))
        }
        recycler_view_3.setHasFixedSize(true)


        recycler_view_head.layoutManager = GridLayoutManager(id_recycler_view.context, 3)
        recycler_view_head.adapter = BaseDemoListAdapter(buildTestData(5))
        recycler_view_head.setHasFixedSize(true)

        val layoutManager1 = LinearLayoutManager(id_recycler_view.context)
        id_recycler_view.layoutManager = layoutManager1
        id_recycler_view.adapter = BaseDemoListAdapter(buildTestData(30))
        id_recycler_view.setHasFixedSize(true)

        id_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                TabLayoutDemoActivity.RefreshEnable(
                    layoutManager1.findFirstVisibleItemPosition() == 0
                            && recycler_view_3.getLocalVisibleRect(Rect())
                ).post()
            }
        })
    }


    private fun buildTestData(count: Int): ArrayList<String> {
        val list = ArrayList<String>()
        (0..count).forEach {
            list.add("- 第 ${it + 1} 项 -")
        }
        return list
    }

    override fun onStart() {
        super.onStart()
        log("TAG_Debug_TestFragment -> onStart")
    }

    override fun onStop() {
        super.onStop()
        log("TAG_Debug_TestFragment -> onStop")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        log("TAG_Debug_TestFragment -> onHiddenChanged($hidden)")

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        log("TAG_Debug_TestFragment -> setUserVisibleHint($isVisibleToUser)")

        if (!isVisibleToUser) {
            TabLayoutDemoActivity.RefreshEnable(true).post()
            return
        }

        id_recycler_view?.layoutManager?.let {

            // 如果变成当前fragment，且列表第一项已显示出来，则可以refresh
            val isVisibleFirst =
                (id_recycler_view.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0

            TabLayoutDemoActivity.RefreshEnable(isVisibleFirst).post()
        }

    }

    override fun onResume() {
        super.onResume()
        log("TAG_Debug_TestFragment -> onResume")
    }

    override fun onPause() {
        super.onPause()
        log("TAG_Debug_TestFragment -> onPause")
    }
}