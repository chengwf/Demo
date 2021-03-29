package com.chengwf.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseMVVMActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected open lateinit var mViewModel: VM
    protected open lateinit var mBinding: DB

    /**
     * 获得初始化ViewModel的class
     */
    protected abstract fun getViewModelClass(): Class<VM>

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        created()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    protected abstract fun created()
}