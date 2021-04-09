package com.chengwf.animationdemo

import android.view.View
import androidx.core.util.Pair
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class AnimatorDemoListActivity : BaseDemoListActivity() {
    override fun getAdapterList() =
        resources.getStringArray(R.array.animator_demo_list).toMutableList()

    override fun getActivityTitle() = "动画相关Demo"

    override fun onClickChild(position: Int, view: View, item: String) {
        val pair = Pair<View, String>(
            view.findViewById(R.id.id_text_view),
            Const.TRANSITION_NAME_TITLE
        )
        println("=======#position========$item===============")
        when (position) {
            0 -> launchActivity<DynamicAnimationActivity>(pair)
            1 -> launchActivity<BezierCurveDemoActivity>(pair)
        }

    }
}