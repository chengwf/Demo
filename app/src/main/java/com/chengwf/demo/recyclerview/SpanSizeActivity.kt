package com.chengwf.demo.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_span_size.*

class SpanSizeActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_span_size
    private val mList = ArrayList<SpanSizeData>()
    private val mAdapter by lazy { SpanSizeAdapter(mList) }
    override fun initView() {

        mList.add(SpanSizeData(R.drawable.ic_launcher_background, "1"))
        mList.add(SpanSizeData(R.drawable.ic_launcher_background, "2"))
        mList.add(SpanSizeData(R.drawable.ic_launcher_background, "3"))
        mList.add(SpanSizeData(R.drawable.ic_launcher_background, "4"))

        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { finish() }
        id_recycler_view.adapter = mAdapter
        mAdapter.animationEnable = false

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                if (position != mList.lastIndex) return 1

                if (mList.size % 2 == 1) return manager.spanCount

                return 1
            }
        }
        id_recycler_view.layoutManager = manager

        mAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> {
                    // 加一个
                    mList.add(SpanSizeData(R.drawable.ic_launcher_background, "${mList.size + 1}"))
                    mAdapter.notifyItemInserted(mList.lastIndex)
                    mAdapter.notifyItemChanged(mList.lastIndex)
                }
                else -> {
                    mAdapter.notifyItemRemoved(position)
                    // 删一个
                    mList.removeAt(position)
                }
            }
        }
    }

    data class SpanSizeData(val int: Int, val text: String)
}