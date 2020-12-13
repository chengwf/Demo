package com.chengwf.demo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStatusBar()
        createView()
        return inflater.inflate(getLayoutResId(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreate(view)
    }

    abstract fun viewCreate(view: View)

    protected open fun setStatusBar() {}
    protected open fun createView() {}
    abstract fun getLayoutResId(): Int
}