package com.chengwf.demo.viewchat

import android.view.MotionEvent
import android.view.View
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseActivity
import com.chengwf.demo.viewchat.ui.main.SectionsPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_chat_more.*
import kotlin.random.Random

class MoreChatActivity : BaseActivity() {


    override fun initView() {

        val list = ArrayList<View>()

        list.add(layoutInflater.inflate(R.layout.fragment_test_1, null, false))
        list.add(layoutInflater.inflate(R.layout.fragment_test_2, null, false))
        list.add(layoutInflater.inflate(R.layout.fragment_test_3, null, false))

        val sectionsPagerAdapter = SectionsPagerAdapter(list)
        id_view_pager.adapter = sectionsPagerAdapter
        id_floating_action.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        id_button.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                id_view_pager.setCurrentItem(Random.nextInt(3), true)
            }
            false
        }
    }

    override fun getLayoutResId() = R.layout.activity_chat_more
}