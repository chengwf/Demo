package com.example.databinding

import com.example.databinding.viewmodel.DBDemoVM
import me.redcircle.utils.base.BaseVMActivity

class DataBindingDemoActivity : BaseVMActivity<DBDemoVM>() {
    override fun getLayoutId() = R.layout.activity_data_binding_demo

    override fun getViewModelClass() = DBDemoVM::class.java

    override fun observe() {
    }

    override fun initViews() {
    }

}
