package com.chengwf.databindingdemo.view

import android.view.View
import androidx.core.util.Pair
import com.chengwf.databindingdemo.R
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseDemoListActivity
import com.chengwf.utils.ext.launchActivity

class DemoListActivity : BaseDemoListActivity() {


    override fun getAdapterList() = arrayOf("字段，非列表", "RecyclerView", "DiffUtil").toMutableList()

    override fun getActivityTitle() = "DataBinding相关"

    override fun onClickChild(position: Int, view: View, item: String) {
        val pair: Pair<View, String> = Pair(
            view.findViewById(R.id.id_text_view),
            Const.TRANSITION_NAME_TITLE
        )

        when (position) {
            0 -> launchActivity<BindFieldActivity>(pair)
            1 -> launchActivity<ListActivity>(pair)
            2 -> launchActivity<DiffUtilActivity>(pair)
        }
    }
}
