package com.chengwf.databindingdemo.view

import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengwf.databindingdemo.DemoAdapter
import com.chengwf.databindingdemo.R
import com.chengwf.databindingdemo.entity.TestItemBean
import com.chengwf.databindingdemo.viewmodel.ListVM
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseVMActivity
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseVMActivity<ListVM>() {

    private val mAdapter = DemoAdapter()

    override fun getViewModelClass() = ListVM::class.java

    override fun getLayoutId() = R.layout.activity_list

    override fun observe() {
    }

    override fun initViews() {
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()

        id_recycler_view.layoutManager = LinearLayoutManager(this)
        id_recycler_view.adapter = mAdapter

        val list = ArrayList<TestItemBean>()
        (0..6).forEach {
            list.add(TestItemBean(ObservableInt(it), ObservableField("第${it + 1}条")))
        }
        mAdapter.setList(list)
        mAdapter.setOnItemClickListener { _, _, position ->
            // 直接在这里就可以改变列表数据
            mAdapter.data[position].text.let {
                mAdapter.data[position].index.set(mAdapter.data[position].index.get() + 1)
                it.set("点了 ${mAdapter.data[position].index.get() - position} 下")
            }
        }


        id_toolbar.setNavigationOnClickListener { ActivityCompat.finishAfterTransition(this) }
        id_toolbar.title = "databinding - 列表绑定"
    }
}
