package com.chengwf.demo.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chengwf.demo.R

class SpanSizeAdapter(list: ArrayList<Int>) :
    BaseQuickAdapter<Int, BaseViewHolder>(R.layout.adapter_image_view, list) {
    override fun convert(holder: BaseViewHolder, item: Int) {
        holder.setImageResource(R.id.id_image_view, item)
    }
}