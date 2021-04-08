package com.chengwf.designviewdemo.other

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.view.postDelayed
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.chengwf.designviewdemo.R
import com.chengwf.utils.BaseEvent
import com.chengwf.utils.adapter.CommonPageAdapter
import com.chengwf.utils.base.BaseActivity
import com.chengwf.utils.callback.ViewPageChangeListener
import com.chengwf.utils.ext.getStatusBarHeight
import com.chengwf.utils.ext.log
import kotlinx.android.synthetic.main.activity_tab_layout_demo.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TabLayoutDemoActivity : BaseActivity() {


    override fun getLayoutId() =
        R.layout.activity_tab_layout_demo

    override fun initViews() {
        EventBus.getDefault().register(this)
        id_tab_layout.updateLayoutParams<LinearLayout.LayoutParams> {
            height += getStatusBarHeight()
        }

        id_tab_layout.setPadding(0, getStatusBarHeight(), 0, 0)

        val adapter = CommonPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
        )
        adapter.addFragment(TestFragment1.instant)
        adapter.addFragment(TestFragment2())
        id_view_pager.adapter = adapter

        id_view_pager.addOnPageChangeListener(ViewPageChangeListener {
            onSelected = { position ->
                if (position == 0) {
                    setupRefreshEnable(TestFragment1.instant.isRefresh())
                }
            }
        })

        initMagicIndicator()

        id_swipe_refresh.setOnRefreshListener {
            id_swipe_refresh.postDelayed(2000) { id_swipe_refresh.isRefreshing = false }
        }
    }

    private fun initMagicIndicator() {
        val commonNav = CommonNavigator(this)
        commonNav.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                return ScaleTransitionPagerTitleView(
                    this@TabLayoutDemoActivity
                ).apply {
                    text = "fragment $index"
                    textSize = 20f
                    normalColor = Color.WHITE
                    selectedColor = Color.WHITE

                    setOnClickListener { id_view_pager.setCurrentItem(index, true) }
                }
            }

            override fun getCount() = 2

            override fun getIndicator(context: Context?): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineHeight = UIUtil.dip2px(context, 2.0).toFloat()
                    lineWidth = UIUtil.dip2px(context, 30.0).toFloat()
                    roundRadius = UIUtil.dip2px(context, 6.0).toFloat()
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2.0f)
                    setColors(Color.WHITE)
                }
            }

        }

        id_tab_layout.navigator = commonNav
        ViewPagerHelper.bind(id_tab_layout, id_view_pager)
    }

    data class RefreshEnable(val isRefreshEnable: Boolean = false) : BaseEvent()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshEnableEvent(event: RefreshEnable) {

        setupRefreshEnable(event.isRefreshEnable)
    }

    override fun onStop() {
        super.onStop()
        if (isFinishing) {
            EventBus.getDefault().unregister(this)
        }
    }

    private fun setupRefreshEnable(isEnableRefresh: Boolean) {

        if (id_swipe_refresh.isRefreshing) {
            return
        }

        id_swipe_refresh.isEnabled = isEnableRefresh
    }
}
