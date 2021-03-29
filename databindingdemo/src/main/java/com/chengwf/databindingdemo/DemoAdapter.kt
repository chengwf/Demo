package com.chengwf.databindingdemo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chengwf.databindingdemo.databinding.AdapterDatabindingListBinding
import com.chengwf.databindingdemo.entity.TestItemBean


class DemoAdapter :
    BaseQuickAdapter<TestItemBean, BaseDataBindingHolder<AdapterDatabindingListBinding>>(R.layout.adapter_databinding_list) {
    override fun convert(
        holder: BaseDataBindingHolder<AdapterDatabindingListBinding>,
        item: TestItemBean
    ) {
        holder.dataBinding?.item = item
    }

}
