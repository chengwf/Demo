package com.chengwf.utils.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chengwf.utils.R

class BaseDemoListAdapter(data: ArrayList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_base_demo_list, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.id_text_view, item)
        addChildClickViewIds(R.id.id_text_view)
    }
}