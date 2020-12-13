package com.chengwf.demo.snack_bar

import android.view.View
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity
import com.sun.easysnackbar.BaseTransientBar
import com.sun.easysnackbar.EasySnackBar
import kotlinx.android.synthetic.main.activity_top_snack_bar.*


class TopSnackBarActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_top_snack_bar
    override fun initView() {
        id_button.setOnClickListener {

            val contentView: View =
                EasySnackBar.convertToContentView(root_view, R.layout.layout_snack_bar_top)

            EasySnackBar.make(id_layout_1, contentView, BaseTransientBar.LENGTH_LONG, true).show()

        }
    }

}