package com.chengwf.databindingdemo.view

import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.chengwf.databindingdemo.R
import com.chengwf.databindingdemo.databinding.ActivityBindFieldBinding
import com.chengwf.databindingdemo.entity.ObservableFieldBean
import com.chengwf.databindingdemo.viewmodel.DBDemoVM
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseMVVMActivity
import com.chengwf.utils.ext.circle
import com.chengwf.utils.ext.diggingScreen
import com.chengwf.utils.ext.setCircularCorner
import com.chengwf.utils.ext.toPix
import kotlinx.android.synthetic.main.activity_bind_field.*

class BindFieldActivity : BaseMVVMActivity<DBDemoVM, ActivityBindFieldBinding>() {

    private val mData = ObservableFieldBean(ObservableInt(0), ObservableField("0"))


    override fun getViewModelClass() = DBDemoVM::class.java

    override fun getLayoutId() =
        R.layout.activity_bind_field

    override fun created() {
        mBinding.data = mData

        id_toolbar.setNavigationOnClickListener { ActivityCompat.finishAfterTransition(this) }
        id_toolbar.diggingScreen()
        id_appbar_layout.diggingScreen()
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
        id_tool_1.setOnClickListener {
            mData.index.set(mData.index.get() + 1)
            mData.name.set(mData.index.get().toString())
        }
        id_tool_2.setOnClickListener {
            mData.index.set(mData.index.get() - 1)
            mData.name.set(mData.index.get().toString())
        }

        id_tool_1.circle()

        id_tool_2.setCircularCorner(30.toPix())
    }
}
