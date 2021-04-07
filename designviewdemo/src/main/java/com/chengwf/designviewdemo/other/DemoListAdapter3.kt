package com.chengwf.designviewdemo.other

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chengwf.designviewdemo.R

class DemoListAdapter3 (list:ArrayList<String>):BaseQuickAdapter<String,BaseViewHolder>(
    R.layout.adapter_demo_list_3,list) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setImageResource(
            R.id.id_image_view,
            R.drawable.ic_menu_common
        )
    }
}
