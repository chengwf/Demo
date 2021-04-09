package com.chengwf.customview

import android.view.View
import androidx.core.util.Pair
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity2

class LotteryPanListActivity : BaseDemoListActivity() {

  override fun onClickChild(position: Int, view: View, item: String) {
        val pair: Pair<View, String> = Pair(
            view.findViewById(R.id.id_text_view),
            Const.TRANSITION_NAME_TITLE
        )

        when (position) {
            0 -> launchActivity2<TurntableActivity>(pair)
            1 -> launchActivity2<PieViewActivity>(pair)
        }
    }

    override fun getAdapterList() = arrayOf("用SurfaceView实现（未完成）", "Canvas实现，可指定目标").toMutableList()

    override fun getActivityTitle() = "转盘相关View"
}
