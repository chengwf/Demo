package com.chengwf.animationdemo

import androidx.core.view.ViewCompat
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_bezier_curve_demo.*

class BezierCurveDemoActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_bezier_curve_demo

    override fun initViews() {
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { onBackPressed() }
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
    }
}