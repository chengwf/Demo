package com.chengwf.demo.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chengwf.demo.R

class SpanSizeAdapter(list: ArrayList<SpanSizeActivity.SpanSizeData>) :
    BaseQuickAdapter<SpanSizeActivity.SpanSizeData, BaseViewHolder>(R.layout.adapter_image_view, list) {
    override fun convert(holder: BaseViewHolder, item: SpanSizeActivity.SpanSizeData) {
        holder.setImageResource(R.id.id_image_view, item.int)
        holder.setText(R.id.id_text_view, item.text)
    }
}