package com.chengwf.designviewdemo.other

import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chengwf.designviewdemo.R
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab_test_1.*
import org.greenrobot.eventbus.EventBus

class TestFragment1 : BaseFragment() {
    override fun getLayoutId() =
        R.layout.fragment_tab_test_1

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

        id_recycler_view.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                EventBus.getDefault().post(TabLayoutDemoActivity.RefreshEnable(layoutManager1.findFirstVisibleItemPosition() == 0))
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
}