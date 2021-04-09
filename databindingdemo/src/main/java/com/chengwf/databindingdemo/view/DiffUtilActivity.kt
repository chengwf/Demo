package com.chengwf.databindingdemo.view

import androidx.core.view.ViewCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengwf.databindingdemo.MyDiffUtil
import com.chengwf.databindingdemo.R
import com.chengwf.databindingdemo.viewmodel.DiffUtilVM
import com.chengwf.utils.Const
import com.chengwf.utils.adapter.BaseDemoListAdapter
import com.chengwf.utils.base.BaseVMActivity
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_diff.*

class DiffUtilActivity : BaseVMActivity<DiffUtilVM>() {
    override fun getViewModelClass() = DiffUtilVM::class.java


    override fun getLayoutId() = R.layout.activity_diff

    private val mAdapter by lazy { BaseDemoListAdapter(buildTestData(6)) }
    override fun initViews() {

        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
        id_recycler_view.layoutManager = LinearLayoutManager(this)
        id_recycler_view.setHasFixedSize(true)
        id_recycler_view.adapter = mAdapter

        fab_1.setOnClickListener {
            val diffUtil = DiffUtil.calculateDiff(
                MyDiffUtil(
                    mAdapter.data as ArrayList,
                    updateTestData()
                ), true
            )

            diffUtil.dispatchUpdatesTo(mAdapter)
        }
    }


    override fun observe() {
        mViewModel.listLiveData.observe(this) {
        }
    }

    private fun buildTestData(count: Int): ArrayList<String> {
        val list = ArrayList<String>()

        (0..count).forEach {
            list.add("- 第${it + 1}行 -")
        }
        return list
    }

    private fun updateTestData(): ArrayList<String> {

        val list = ArrayList<String>()
        (2..7).forEach {
            list.add("- 第${it + 1}行 -")
        }
        return list
    }
}