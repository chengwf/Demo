package com.chengwf.utils.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OverScrollLayout : LinearLayout {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attr: AttributeSet) : super(context, attr, 0)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) :
            super(context, attr, defStyleAttr)

    companion object {
        private const val ANIM_TIME: Long = 400
    }

    private val mOriginal = Rect()

    private var isMoved = false

    private var mStartYpos: Float = 0F

    /**  阻尼系数 **/
    private val DAMPING_COEFFICIENT = 0.3f

    private var isSuccess = false

    private lateinit var mChildView: RecyclerView

    override fun onFinishInflate() {
        super.onFinishInflate()
        mChildView = getChildAt(0) as RecyclerView
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mChildView.let {
            mOriginal.set(it.left, it.top, it.right, it.bottom)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        ev?.also { event ->

            val touchYpos = event.y

            if (touchYpos >= mOriginal.bottom || touchYpos <= mOriginal.top) {
                if (isMoved) {
                    recoverLayout()
                }
                return true
            }


            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mStartYpos = event.y
                }

                MotionEvent.ACTION_MOVE -> {
                    val scrollYpos = event.y - mStartYpos
                    val pullDown = scrollYpos > 0 && canPullDown()
                    val pullUp = scrollYpos < 0 && canPullUp()

                    if (pullDown || pullUp) {
                        cancelChild(ev)

                        val offset = (scrollYpos * DAMPING_COEFFICIENT).toInt()

                        mChildView.layout(
                            mOriginal.left,
                            mOriginal.top + offset,
                            mOriginal.right,
                            mOriginal.bottom + offset
                        )

                        mOnScroll()

                        isMoved = true
                        isSuccess = false
                        return true
                    } else {
                        mStartYpos = ev.y
                        isMoved = false
                        isSuccess = true
                        return super.dispatchTouchEvent(ev)
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (isMoved) {
                        recoverLayout()
                    }
                    return !isSuccess || super.dispatchTouchEvent(ev)
                }
                else -> return true
            }


        }

        return super.dispatchTouchEvent(ev)
    }

    /**
     * 取消子view已经处理的事件
     *
     * @param ev event
     */
    private fun cancelChild(ev: MotionEvent) {
        ev.action = MotionEvent.ACTION_CANCEL
        super.dispatchTouchEvent(ev)
    }


    /**
     * 位置还原
     */
    private fun recoverLayout() {
        val anim = TranslateAnimation(0f, 0f, (mChildView.top - mOriginal.top).toFloat(), 0f)
        anim.duration = ANIM_TIME
        mChildView.startAnimation(anim);
        mChildView.layout(mOriginal.left, mOriginal.top, mOriginal.right, mOriginal.bottom);
        isMoved = false
    }


    /**
     * 判断是否可以下拉
     *
     * @return true：可以，false:不可以
     */
    private fun canPullDown(): Boolean {
        val firstVisiblePosition =
            (mChildView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
        if (firstVisiblePosition != 0 && mChildView.adapter?.itemCount != 0) {
            return false
        }
        val mostTop = if (mChildView.childCount > 0) {
            mChildView.getChildAt(0).top
        } else {
            0
        }
        return mostTop >= 0
    }


    /**
     * 判断是否可以上拉
     *
     * @return true：可以，false:不可以
     */
    private fun canPullUp(): Boolean {
        val lastItemPosition = mChildView.adapter?.itemCount ?: 0 - 1
        val lastVisiblePosition =
            (mChildView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        if (lastVisiblePosition >= lastItemPosition) {
            val childIndex =
                lastVisiblePosition - (mChildView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
            val childCount = mChildView.childCount
            val index = childIndex.coerceAtMost(childCount - 1)
            val lastVisibleChild = mChildView.getChildAt(index)

            if (lastVisibleChild != null) {
                return lastVisibleChild.bottom <= mChildView.bottom - mChildView.top
            }
        }

        return false
    }

    private var mOnScroll: () -> Unit = {}

    private fun addScrollListener(onScroll: () -> Unit) {
        mOnScroll = onScroll
    }
}