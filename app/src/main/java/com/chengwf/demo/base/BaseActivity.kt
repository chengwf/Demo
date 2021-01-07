package com.chengwf.demo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setStatus()

        initView()
    }

    abstract fun initView()

    protected open fun setStatus() {}
    protected open fun initViewModel() {}

    abstract fun getLayoutResId(): Int
}