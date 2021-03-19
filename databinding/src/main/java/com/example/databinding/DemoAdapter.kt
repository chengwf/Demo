package com.example.databinding

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.databinding.repository.TestData

class DemoAdapter(list: ArrayList<TestData>) :
    BaseQuickAdapter<TestData, BaseViewHolder>(R.layout.adapter_test) {
    override fun convert(holder: BaseViewHolder, item: TestData) {
        holder.setText(R.id.tv_adapter_index, (item.index + 1).toString())
            .setText(R.id.id_text_view, item.desc)
    }


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
//        DataBindingUtil.bind<DemoAdapter>(viewHolder.itemView)
    }
}