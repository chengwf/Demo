package me.redcircle.utils.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.chengwf.utils.base.BaseActivity

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
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    /**
     * 获得初始化ViewModel的class
     */
    protected abstract fun getViewModelClass(): Class<VM>

    /**
     * LiveData发生变化通知界面改变
     */
    protected abstract fun observe()
}