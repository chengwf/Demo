package com.chengwf.utils.dialog

import android.content.Intent
import com.chengwf.utils.BaseEvent
import com.chengwf.utils.R
import com.chengwf.utils.base.BaseActivity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoadingActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_loading

    override fun initViews() {
        // 点击外部区域不消失
        setFinishOnTouchOutside(false)
    }

    override fun onBackPressed() {
        if (!intent.getBooleanExtra("isBack", false))
            return
        super.onBackPressed()
    }

    /**
     * 暂时还不知道怎么关闭，在设置返回键关闭之后，先这么做，以后再优化
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun isShow(event: Event) {
        finish()
    }

    data class Event(val status: String) : BaseEvent()

    private val showNumber = ArrayList<Int>()

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        showNumber.add(showNumber.size)
    }

    override fun onDestroy() {
        super.onDestroy()
        showNumber.clear()
    }

    companion object {
        fun dismiss(flag: String = "dismiss") = Event(flag).post()
    }
}
