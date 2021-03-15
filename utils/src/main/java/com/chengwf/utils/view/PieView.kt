package com.chengwf.utils.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.chengwf.utils.callback.AnimatorListen
import com.chengwf.utils.ext.rotate
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by chenzipeng on 2018/7/27.
 * function:
 */
class PieView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mCount = 0
    private val sectorColor by lazy {
        intArrayOf(
            Color.parseColor("#FF5169")
            , Color.parseColor("#FDB53E")
            , Color.parseColor("#39B0C4")

            , Color.parseColor("#5AD8A6")
            , Color.parseColor("#5C73F4")
            , Color.parseColor("#C53FF5")

            , Color.parseColor("#F95BAA")
            , Color.parseColor("#BAE340")
            , Color.parseColor("#22D68A")

            , Color.parseColor("#25D7DD")
            , Color.parseColor("#7F52C9")
            , Color.parseColor("#DE5EEB")
        )
    }

    /**
     * 绘制扇形
     */
    private val mArcPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    /**
     * 绘制文字
     */
    private val mTextPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            textSize = mTextSize
        }
    }

    /**
     * 半径
     */
    private var mRadius = 0

    /**
     * 圆心坐标
     */
    private var mCenter = 0

    private val mData by lazy { ArrayList<CirclePanData>() }

    /**
     * 弧形的起始角度
     */
    private var startAngle: Float = 0F
    private val angles: FloatArray = FloatArray(12)
    private val mTextSize =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 48f, resources.displayMetrics)
    private val sectorRectF by lazy {
        //2.2设置扇形绘制的范围
        RectF(
            paddingLeft.toFloat(), paddingLeft.toFloat(),
            (mCenter * 2 - paddingLeft).toFloat(), (mCenter * 2 - paddingLeft).toFloat()
        )
    }

    /**
     * 弧形划过的角度,270度就是正上方
     */
    private var sweepAngle: Float = 270F


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val width = w.coerceAtMost(h)
        mCenter = width / 2

        //半径
        mRadius = (width - paddingLeft * 2) / 2

        //设置框高都一样
        setMeasuredDimension(width, width)
    }


    fun addPanData(item: CirclePanData) {
        if (mData.any { it.userId == item.userId }) {
            return
        }
        mData.add(item)
        refresh()
    }

    fun getData() = mData

    fun number() = getData().size

    fun removeMember(userId: String) {
        mData.firstOrNull { it.userId == userId }?.let { mData.remove(it) }
        refresh()
    }

    /**
     * 是否存在于盘块数据中
     */
    fun isExist(userId: String) = mData.any { it.userId == userId }

    private fun refresh() {
        mCount = mData.size
        sweepAngle = 360F / mCount
        sweepAngle = if (mCount == 0) {
            0f
        } else {
            360F / mCount
        }
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        println("=========mCount = $mCount===============")

        //2.绘制扇形
        //2.1设置每一个扇形的角度
        startAngle = 270F - (sweepAngle / 2)

        for (i in 0 until mCount) {
            mArcPaint.color = sectorColor[i]
            //sectorRectF 扇形绘制范围  startAngle 弧开始绘制角度 sweepAngle 每次绘制弧的角度
            // useCenter 是否连接圆心
            canvas.drawArc(sectorRectF, startAngle, sweepAngle, true, mArcPaint)
            //3.绘制文字
//            drawTexts(canvas, mData[i].userId)
            //4.绘制图片
            if (mCount > 0) {
                mData[i].bitmap?.let { drawIcons(canvas, it, (sweepAngle * i)) }
            }

            angles[i] = startAngle
            Log.d(TAG, "onDraw: " + angles[i] + "     " + i)
            startAngle += sweepAngle
        }
        super.onDraw(canvas)
        Log.d(TAG, "startAngle: $startAngle   sweepAngle  $sweepAngle")
    }

    /**
     * 以二分之一的半径的长度，扇形的一半作为图片的中心点
     * 图片的宽度为imageWidth
     *
     * @param canvas 1
     * @param mBitmap 2
     */
    private fun drawIcons(canvas: Canvas, mBitmap: Bitmap, alpha: Float) {
        val imageWidth = mRadius / 5
        //计算半边扇形的角度 度=Math.PI/180 弧度=180/Math.PI
        val angle = ((startAngle + sweepAngle / 2) * Math.PI / 180).toFloat()
        //计算中心点的坐标
        val x = (mCenter + (mRadius / 3 * 2) * cos(angle.toDouble())).toFloat()
        val y = (mCenter + (mRadius / 3 * 2) * sin(angle.toDouble())).toFloat()
        //设置绘制图片的范围
        val rectF = RectF(x - imageWidth, y - imageWidth, x + imageWidth, y + imageWidth)
        canvas.drawBitmap(mBitmap.rotate(alpha), null, rectF, null)
//        canvas.drawBitmap(mBitmap, null, rectF, null)
    }

    private var index = 0
    private var isRunning = false

    /**
     * 开始动画
     */
    fun rotate(i: Int) {

        // 只有动画停止的时候才可以转动
        if (isRunning) return

        val a: Float = sweepAngle * when {
            // 当前指向的index大于目标的index
            index - i > 0 -> {
                -(mCount - i).toFloat()
            }
            // 当前index和下一个目标index一致，比如上一个目标index被移除，此次又选中了这个index
            index - i == 0 -> 0F

            // 当前index是2，目标index是1
            else -> {
                (mCount + i).toFloat()
            }
        }


        Log.d(
            TAG,
            "转动目标 --》第${i}个 ,当前第${index}个, 计算出来的要多旋转的度数：$a 除以盘块的度数 $sweepAngle 为${(a / sweepAngle)}"
        )
        index = i

        val toDegree = 360f * 7 - a

        val animator = ObjectAnimator.ofFloat(this@PieView, "rotation", 0f, toDegree).apply {
            duration = 3000
            repeatCount = 0
            interpolator = DecelerateInterpolator()
            setAutoCancel(true)
        }
        animator.start()
        animator.addListener(AnimatorListen(end = {
            isRunning = false

            if (mData.isEmpty()) return@AnimatorListen

            Log.d(TAG, "转动结果 --》 第${index}个 id是：${mData[index].userId}")
            // TODO 停止转动后的操作
        }))
    }

    companion object {
        private val TAG = PieView::class.java.simpleName
    }

    fun reset() {

        ObjectAnimator.ofFloat(this@PieView, "rotation", 0f, 360F).apply {
            duration = 100
            repeatCount = 0
            interpolator = DecelerateInterpolator()
            setAutoCancel(true)
        }.start()

        mCount = 0
//        sweepAngle = 0
        startAngle = 270F
        mData.clear()
        refresh()
        this.invalidate()
    }
}