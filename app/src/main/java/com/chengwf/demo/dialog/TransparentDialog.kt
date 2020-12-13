package com.chengwf.demo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.chengwf.demo.R
import kotlinx.android.synthetic.main.dialog_transparent.*

class TransparentDialog : Dialog {

    constructor(context: Context) : super(context, R.style.customDialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_transparent)



        id_dialog_submit.setOnClickListener { dismiss() }
//        id_dialog_cancel.setOnClickListener { dismiss() }
    }
}