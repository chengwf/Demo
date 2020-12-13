package com.chengwf.demo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        initView()
    }

    abstract fun initView()

    protected fun setStatus() {}

    abstract fun getLayoutResId(): Int
}