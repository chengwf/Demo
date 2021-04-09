package com.chengwf.designviewdemo

import android.view.View
import androidx.core.util.Pair
import com.chengwf.designviewdemo.other.TabLayoutDemoActivity
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class DesignViewListActivity : BaseDemoListActivity() {
    override fun getActivityTitle() = "DesignView相关"

    override fun getAdapterList(): MutableList<String> {
        val list = ArrayList<String>()
        resources.getStringArray(R.array.DesignViewList).forEach { list.add(it) }
        return resources.getStringArray(R.array.DesignViewList).toMutableList()
    }

    override fun onClickChild(position: Int, view: View, item: String) {
        val pair: Pair<View, String> = Pair(
            view.findViewById(R.id.id_text_view),
            Const.TRANSITION_NAME_TITLE
        )
        launchActivity<TabLayoutDemoActivity>()
    }
}
