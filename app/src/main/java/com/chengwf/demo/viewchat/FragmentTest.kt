package com.chengwf.examolerecord.more_chat

import android.view.View
import com.chengwf.demo.base.BaseFragment

class FragmentTest(private val layoutId: Int) : BaseFragment() {
    override fun viewCreate(view: View) {
    }

    override fun getLayoutResId() = layoutId


    companion object {
        fun newInstance(layoutId: Int) = FragmentTest(layoutId)
    }
}