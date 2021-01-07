package com.chengwf.demo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.chengwf.demo.R
import kotlinx.android.synthetic.main.dialog_progress.*


class ProgressBarDialog1(
    context: Context,
    private val isCancelable: Boolean = false,
    private val isTouchOutside: Boolean = false,
    private val msg: String = "",
    private val okStr: String = "",
    private val cancelStr: String = "",
    private val icon: Int = R.mipmap.ic_launcher_round,
    private val OK: () -> Unit = {},
    private val cancel: () -> Unit = {}
) :
    Dialog(context, R.style.ProgressDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        setCancelable(isCancelable)
        setCanceledOnTouchOutside(isTouchOutside)
        window?.attributes?.gravity = Gravity.CENTER

        id_dialog_icon.setImageResource(icon)

        if (icon == R.mipmap.ic_launcher_round) {
            val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.progress_bar_1)
            anim.start()
            id_dialog_icon.startAnimation(anim)
        }
    }
}