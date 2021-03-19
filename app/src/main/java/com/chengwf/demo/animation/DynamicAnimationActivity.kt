package com.chengwf.demo.animation

import android.content.res.Resources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity
import com.chengwf.utils.ext.getStatusBarHeight
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_dynamic_animation.*
import kotlinx.android.synthetic.main.activity_dynamic_animation.id_layout_1
import kotlinx.android.synthetic.main.activity_dynamic_animation.id_toolbar

class DynamicAnimationActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_dynamic_animation

    override fun setStatus() {
        (id_layout_1.layoutParams as CoordinatorLayout.LayoutParams).height =
            id_layout_1.layoutParams.height + getStatusBarHeight()

        (id_toolbar.layoutParams as AppBarLayout.LayoutParams).topMargin = getStatusBarHeight()
    }

    override fun initView() {
        val springForce = SpringForce(0f)
            .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
            .setStiffness(SpringForce.STIFFNESS_HIGH)
        val anim = SpringAnimation(id_button, SpringAnimation.TRANSLATION_Y).setSpring(springForce)


        id_button.setOnClickListener {
            anim.setStartValue(id_button.x)
            anim.setMaxValue(Resources.getSystem().displayMetrics.heightPixels / 2F)
            anim.start()
        }
    }

}