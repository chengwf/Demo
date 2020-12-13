package com.chengwf.demo.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_span_size.*

class SpanSizeActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_span_size
    private val mList = ArrayList<Int>()
    private val mAdapter by lazy { SpanSizeAdapter(mList) }
    override fun initView() {

        mList.add(R.drawable.ic_launcher_background)
        mList.add(R.drawable.ic_launcher_background)
        mList.add(R.drawable.ic_launcher_background)
        mList.add(R.drawable.ic_launcher_background)

        id_recycler_view.adapter = mAdapter

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (mList.size % 2 == 1 && position == mList.lastIndex) {
                    return 2
                }
                return 1
            }
        }
        id_recycler_view.layoutManager = manager

        mAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> {
                    // 加一个
                    mList.add(R.mipmap.ic_launcher_round)
                    mAdapter.notifyItemInserted(mList.lastIndex)
                }
                else -> {
                    // 删一个
                    mList.removeAt(position)
                    mAdapter.notifyItemRemoved(position)
                }
            }
        }
    }

}