package com.chengwf.utils

import org.greenrobot.eventbus.EventBus

open class BaseEvent {
    fun post() {
        EventBus.getDefault().post(this)
    }

    fun postSticky() {
        EventBus.getDefault().postSticky(this)
    }

}