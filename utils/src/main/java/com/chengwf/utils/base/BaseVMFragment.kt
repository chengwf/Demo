package com.chengwf.utils.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {
    protected open lateinit var mViewModel: VM

    /**
     * 获得初始化ViewModel的class
     */
    protected abstract fun getViewModelClass(): Class<VM>

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * LiveData发生变化通知界面改变
     */
    protected abstract fun observe()
}