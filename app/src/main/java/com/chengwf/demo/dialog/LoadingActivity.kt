package com.chengwf.demo.dialog

import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity

class LoadingActivity : BaseActivity() {
    override fun initView() {
        setFinishOnTouchOutside(false)
    }

    override fun onBackPressed() {
        if (intent.getBooleanExtra("cancel", false))
            return
        super.onBackPressed()
    }

    override fun getLayoutResId() = R.layout.activity_loading
}