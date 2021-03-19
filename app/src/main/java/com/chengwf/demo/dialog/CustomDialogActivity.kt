package com.chengwf.demo.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.chengwf.demo.R
import com.chengwf.utils.ext.launchActivity
import kotlinx.android.synthetic.main.activity_custom_dialog.*

class CustomDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_dialog)
        bn_dialog_alert.setOnClickListener { showCustomDialog1() }
        bn_dialog_progress.setOnClickListener { showProgressDialog1() }

        bn_dialog_activity.setOnClickListener { launchActivity<LoadingActivity>() }
        bn_dialog_activity.setOnLongClickListener {
            launchActivity<LoadingActivity> {
                putExtra("cancel", true)
            }
            return@setOnLongClickListener false
        }
    }

    private fun showProgressDialog1() {
        val dialog =
            ProgressBarDialog1(
                this,
                icon = R.drawable.ic_launcher_background,
                isCancelable = false,
                isTouchOutside = true
            )
        dialog.show()
    }

    private fun showCustomDialog1() {
        val dialog = TransparentDialog(this)
        dialog.show()

        // 设置宽高，不然自适应很烦
        val layoutParams = dialog.window?.attributes?.apply {
            gravity = Gravity.CENTER
            width = (Resources.getSystem().displayMetrics.widthPixels * 0.75).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        dialog.window?.attributes = layoutParams
    }
}