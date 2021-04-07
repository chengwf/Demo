package com.chengwf.utils

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BaseDemoListAdapter(data: ArrayList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_base_demo_list, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.id_text_view, item)
    }
}