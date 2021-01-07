package com.chengwf.demo.dialog

import android.os.Bundle
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity

class LoadingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }


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