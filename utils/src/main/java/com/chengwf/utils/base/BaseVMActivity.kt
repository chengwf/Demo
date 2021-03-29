package com.chengwf.utils.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

/**
 * 需要ViewModel的Activity基类
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {

    protected open lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        observe()
    }

    /**
     * 获得初始化ViewModel的class
     */
    protected abstract fun getViewModelClass(): Class<VM>

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    /**
     * LiveData发生变化通知界面改变
     */
    protected abstract fun observe()
}