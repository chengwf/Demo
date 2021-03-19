package com.chengwf.demo

import android.app.Application
import com.coder.zzq.toolkit.Toolkit

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        Toolkit.init(this)
    }
}