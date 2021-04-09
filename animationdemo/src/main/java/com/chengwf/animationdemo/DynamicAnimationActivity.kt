package com.chengwf.animationdemo

import android.content.res.Resources
import androidx.core.view.ViewCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.ext.diggingScreen
import kotlinx.android.synthetic.main.activity_dynamic_animation.*

class DynamicAnimationActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_dynamic_animation

    override fun initViews() {

        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        id_toolbar.setNavigationOnClickListener { onBackPressed() }
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
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