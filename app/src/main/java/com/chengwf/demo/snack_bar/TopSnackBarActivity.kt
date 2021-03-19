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

/*
建房
{"anchors":[{"age":20,"accountStatus":"NORMAL","balance":{"buyCandy":false,"integral":"0","subscriptions":{},"candy":"0"},"userAvatar":"http:\/\/dev.qiniu.sugartimeapp.com\/1342325862305869826A24C231D-9F64-4AD7-8B40-0A5ABE99D43A.png","userSex":0,"countryName":"China","userNickname":"饿哦哦哦","photoWalls":[],"id":"1342325862305869826","countryCode":"CN","del":false,"freezingTime":1608783999000,"account":"1069"}],"roomId":"917089470","sessionId":"1343400409503326210","roomName":"饿哦哦哦","onlineQuantity":1,"userId":"1342325862305869826"}
邀请
{"anchors":[{"userNickname":"666","gender":"1","age":27,"userAvatar":"http:\/\/dev.qiniu.sugartimeapp.com\/1608802524651.jpg","userId":"1339857817561939970","countryCode":"CN"}],"sessionId":"1343400521008898050","countryCode":"CN","roomName":"666","streamUsers":[{"id":"1339857817561939970","userId":"1339857817561939970","userAvatar":"http:\/\/dev.qiniu.sugartimeapp.com\/1608802524651.jpg","countryCode":"CN","gender":"1","userNickname":"666","age":27,"sessionId":"1343400521008898050"}],"watchUsers":[{"userId":"1342325862305869826","id":"1342325862305869826","balance":{"candy":"0","subscriptions":{},"buyCandy":false,"integral":"0"},"age":20,"photoWalls":[],"userSex":0,"countryName":"China","countryCode":"CN","del":false,"freezingTime":1608783999000,"account":"1069","userNickname":"饿哦哦哦","userAvatar":"http:\/\/dev.qiniu.sugartimeapp.com\/1342325862305869826A24C231D-9F64-4AD7-8B40-0A5ABE99D43A.png","accountStatus":"NORMAL"}],"userId":"1339857817561939970","onlineQuantity":2,"age":27,"roomId":"118283589","userAvatar":"http:\/\/dev.qiniu.sugartimeapp.com\/1608802524651.jpg","userNickname":"666"}


 */