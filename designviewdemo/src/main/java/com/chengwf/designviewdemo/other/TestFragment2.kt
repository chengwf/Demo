package com.chengwf.designviewdemo.other

import com.chengwf.designviewdemo.R
import com.chengwf.utils.base.BaseFragment
import com.coder.zzq.smartshow.toast.SmartToast
import kotlinx.android.synthetic.main.fragment_tab_test_2.*

class TestFragment2 : BaseFragment() {
    override fun getLayoutId() =
        R.layout.fragment_tab_test_2

    override fun initView() {
        id_button.setOnClickListener { SmartToast.show("fragment 2") }
    }

}