package com.chengwf.utils.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.chengwf.utils.R
import com.chengwf.utils.ext.hideKeyboard

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
//        toolbar?.let {
//            setSupportActionBar(it)
//            toolbar.setNavigationOnClickListener { onBackPressed() }
//            toolbar.title = getToolBarTitle()
//            toolbar.setNavigationIcon(R.mipmap.ic_launcher)
//        }
        initViews()
    }

    protected abstract fun initViews()
    protected abstract fun getLayoutId(): Int
    protected open fun getToolBarTitle(): String = getString(R.string.app_name)


    override fun getResources(): Resources {
        val res = super.getResources()
        if (res.configuration.fontScale != 1f) { //非默认值
            val newConfig = Configuration()
            newConfig.setToDefaults() //设置默认
            createConfigurationContext(newConfig)
        }
        return res
    }

    /** 点击屏幕监听，用来收起软键盘，和一些其他的操作 **/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v?.windowToken)
                hideView()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 点击屏幕的监听
     *
     **/
    protected open fun hideView() {}

    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {

        if (v == null) return false
        if (v !is EditText) return false

        val l = intArrayOf(0, 0)
        v.getLocationInWindow(l)
        val left = l[0]
        val top = l[1]
        val bottom = top + v.getHeight()
        val right = left + v.getWidth()

        return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
    }
}