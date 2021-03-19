package com.chengwf.customview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.view.LuckyItemBean
import com.chengwf.utils.view.LuckyPanView
import kotlinx.android.synthetic.main.activity_turntable.*
import kotlin.random.Random

class TurntableActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_turntable
    private val mData = ArrayList<LuckyItemBean>()
    override fun initViews() {
        urlList.forEach { urlToBitmap(Random.nextLong(10000, 100000), it) }

        id_button.setOnClickListener { }
        id_show_view.setOnClickListener {
            // 转动
            id_luckypan.setPanData(mData)
            startOrEnd()
        }

        id_luckypan
    }


    /**
     * 开始或者结束轮盘的转动
     */
    private fun startOrEnd() {
        if (!id_luckypan.isStart) {
            getLuckyIndex()
            id_luckypan.luckyStart(luckyIndex)
        } else {
            if (!id_luckypan.isShouldEnd) {
                id_luckypan.luckyEnd()
                isShowResult = false
            }
        }
    }


    /**
     * 获得抽奖的结果,结果是点击开始的时候决定的,如果没有给定值,那么就是真正的随机抽奖
     */
    private fun getLuckyIndex() {
        // 如果没指定index，就随机产生一个index
//        luckyIndex = if (!TextUtils.isEmpty(ed_lottery_index.text.toString())) {
//
//            val tempIndex = Integer.valueOf(ed_lottery_index.text.toString())
//            if (tempIndex < 0 || tempIndex > 6) {
//                (Math.random() * mData.size).toInt()
//            } else {
//                Integer.valueOf(ed_lottery_index.text.toString())
//            }
//        } else {
//            (Math.random() * mData.size).toInt()
//        }
        luckyIndex = 0
    }


    var isShowResult = true

    /**
     * 抽奖的结果通知
     */
    private val luckyEndListener = LuckyPanView.OnLuckyEndListener {
        if (!isShowResult) {
//            SmartSnackbar.get(this@LotteryActivity)
//                .show(id_luckypan.panData[luckyIndex], "确定")
            isShowResult = true
        }
    }
    private var luckyIndex = 0

    private var lotteryTypeId: Long = 0
    private fun urlToBitmap(userId: Long, url: String) {
        Glide.with(this)
            .asBitmap()
            .circleCrop()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    mData.add(LuckyItemBean(userId, resource))
                }
            })
    }

    private val urlList = arrayOf(

        "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxlnuvhbej31hc0u0wuw.jpg"
        ,
        "https://tvax3.sinaimg.cn/large/0072Vf1pgy1fodqo8l523j318g0xc4qp.jpg"

        ,
        "https://tvax2.sinaimg.cn/large/.jpg"
        ,
        "https://tvax2.sinaimg.cn/large/0072Vf1pgy1foxk6x3lejj31kw0w0x5a.jpg"
        ,
        "https://tvax1.sinaimg.cn/large/0072Vf1pgy1foxkj7vgb3j31hc0u0nhs.jpg"
        ,
        "https://tvax3.sinaimg.cn/large/0072Vf1pgy1foxlhe0a6tj31hc0u0qhh.jpg"
/*        ,
        "https://tvax1.sinaimg.cn/large/0072Vf1pgy1foxki7zn13j31kw0w07uc.jpg"
        ,
        "https://tvax1.sinaimg.cn/large/0072Vf1pgy1fodqnep3wpj31jk0ujnpd.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201109%2F24%2F142814z5zugbjnquzp44pp.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614742592&t=e866a0c8fd451dc2966a8a3a6bdd4f78"
        ,
        "https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E6%89%8B%E6%9C%BA%E5%A3%81%E7%BA%B8&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=undefined&latest=undefined&copyright=undefined&cs=1155865518,1098496596&os=1117588635,3385487881&simid=3441366801,490847557&pn=86&rn=1&di=71170&ln=1034&fr=&fmq=1612150545885_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fattach.bbs.miui.com%252Fforum%252F201110%252F20%252F104212ds59m9rtqspt6tzt.jpg%26refer%3Dhttp%253A%252F%252Fattach.bbs.miui.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1614742546%26t%3Dd9a75149460a5d63eb09299167df8685&rpstart=0&rpnum=0&adpicid=0&force=undefined"
        ,
        "https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E6%89%8B%E6%9C%BA%E5%A3%81%E7%BA%B8&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=undefined&latest=undefined&copyright=undefined&cs=1964961921,3267854311&os=730207720,1982213539&simid=3462296038,223919076&pn=155&rn=1&di=101530&ln=1034&fr=&fmq=1612150545885_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fd.paper.i4.cn%252Fmax%252F2014%252F07%252F14%252F140715193325.jpg%26refer%3Dhttp%253A%252F%252Fd.paper.i4.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1614742627%26t%3Dc76a17cb2afd29256d68aa331e7d1fa4&rpstart=0&rpnum=0&adpicid=0&force=undefined"
        ,
        "https://yimian-image.obs.cn-east-2.myhuaweicloud.com/moe/img_330_1929x1080_96_null_normal.jpg?AWSAccessKeyId=6LJRZC0YN3MQXXFOWMIH&Expires=1612150714&Signature=xr1PfE9IeYwr98J9rRhL8YnzQ9s%3D"
*/
    )
}
