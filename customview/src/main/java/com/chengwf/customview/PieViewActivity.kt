package com.chengwf.customview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chengwf.customview.entity.PanData
import com.chengwf.utils.Const
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.ext.diggingScreen
import com.coder.zzq.smartshow.toast.SmartToast
import kotlinx.android.synthetic.main.activity_pan_pieview.*

class PieViewActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_pan_pieview

    private var isRunning = false
    override fun initViews() {
        id_appbar_layout.diggingScreen()
        id_toolbar.diggingScreen()
        ViewCompat.setTransitionName(id_toolbar, Const.TRANSITION_NAME_TITLE)
        urlList.forEach { urlToBitmap(it) }

        view_pan_pie_view.addStateCallback { isRunning = it }

        id_toolbar.setNavigationOnClickListener { ActivityCompat.finishAfterTransition(this) }

        tbn_is_remove.setOnCheckedChangeListener { _, isChecked ->
            view_pan_pie_view.setRemoveState(isChecked)
        }
    }

    private var index = 0

    private val mData = ArrayList<PanData>()
    private fun urlToBitmap(url: String) {
        Glide.with(this)
            .asBitmap()
            .circleCrop()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    mData.add(PanData((mData.size + 1).toString(), resource))

                    if (urlList.size == mData.size) {
                        view_pan_pie_view.setData(mData)
                        index = mData.size
                    }
                }
            })
    }


    val urlList = arrayOf(

        "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxlnuvhbej31hc0u0wuw.jpg"
        , "https://tvax3.sinaimg.cn/large/0072Vf1pgy1fodqo8l523j318g0xc4qp.jpg"
        , "https://tvax4.sinaimg.cn/large/0072Vf1pgy1foxkfylhhxj31hc0u04dv.jpg"

        , "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxk6x3lejj31kw0w0x5a.jpg"
        , "https://tvax1.sinaimg.cn/large/0072Vf1pgy1foxkj7vgb3j31hc0u0nhs.jpg"
        , "https://tvax3.sinaimg.cn/large/0072Vf1pgy1foxlhe0a6tj31hc0u0qhh.jpg"

        , "https://tvax1.sinaimg.cn/large/0072Vf1pgy1foxki7zn13j31kw0w07uc.jpg"
        , "https://tvax1.sinaimg.cn/large/0072Vf1pgy1fodqnep3wpj31jk0ujnpd.jpg"
        , "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxlnuvhbej31hc0u0wuw.jpg"

        , "https://tvax4.sinaimg.cn/large/0072Vf1pgy1fodqn34by0j31hc0xcqg9.jpg"
        , "https://tvax3.sinaimg.cn/large/0072Vf1pgy1foxkexoreoj31hc0u0qn0.jpg"
        , "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxkchw5tqj31hc0u07go.jpg"
    )

    fun rotate1(v: View) {
        rotate(0)
    }

    fun rotate2(v: View) {
        rotate(1)
    }

    fun rotate3(v: View) {
        rotate(2)
    }

    fun rotate4(v: View) {
        rotate(3)
    }

    fun rotate5(v: View) {
        rotate(4)
    }

    fun rotate6(v: View) {
        rotate(5)
    }

    fun rotate7(v: View) {
        rotate(6)
    }

    fun rotate8(v: View) {
        rotate(7)
    }

    fun rotate9(v: View) {
        rotate(8)
    }

    fun rotate10(v: View) {
        rotate(9)
    }

    fun rotate11(v: View) {
        rotate(10)
    }

    fun rotate12(v: View) {
        rotate(11)
    }

    fun rotate13(v: View) {

        if (isRunning) {
            return
        }
        view_pan_pie_view.reset()
    }

    fun rotate14(v: View) {

        if (isRunning) {
            return
        }
        view_pan_pie_view.correction()
    }

    private fun rotate(index: Int) {

        if (isRunning) {
            return
        }

        if (view_pan_pie_view.getMemberCount() <= index) {
            SmartToast.show("没有那么多盘块")
            return
        }

        if (mData.size > index) {
            view_pan_pie_view.rotate(index)
        }
    }
}