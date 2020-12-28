package com.chengwf.demo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class MainMenuAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_main, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.id_button, item)
    }
}