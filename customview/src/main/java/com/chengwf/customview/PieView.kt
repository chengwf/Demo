package com.chengwf.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.chengwf.customview.entity.PanData
import com.chengwf.utils.callback.AnimatorListen
import com.chengwf.utils.ext.rotate
import com.chengwf.utils.ext.second
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin


/**
 * Created by chenzipeng on 2018/7/27.
 * function:
 *
 * 我将这个类改了下，不知道是不是我改出问题了，发现转动到指定位置的功能似乎无效，所以改写了那部分的逻辑
 *
 * 原作者项目的github：
 *  @see https://github.com/SepCzp/CustomControl
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
     * 画背景
     */
    private val mBgPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#FF4500")
        }
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

    private val mData = ArrayList<PanData>()

    /**
     * 弧形的起始角度
     */
    private var startAngle: Float = 0F
    private val angles: FloatArray = FloatArray(12)
    private val mTextSize =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 48f, resources.displayMetrics)
    private lateinit var sectorRectF: RectF

    /** 旋转被选中后是否remove **/
    private var isRemove = false

    /**
     * 弧形划过的角度
     */
    private var sweepAngle: Float = 0F

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val width = w.coerceAtMost(h)
        mCenter = width / 2
        //半径
        mRadius = (width - paddingLeft * 2) / 2
        sectorRectF = RectF(
            paddingLeft.toFloat(), paddingLeft.toFloat(),
            (mCenter * 2 - paddingLeft).toFloat(), (mCenter * 2 - paddingLeft).toFloat()
        )
        //设置框高都一样
        setMeasuredDimension(width, width)
    }


    fun setData(list: List<PanData>) {
        mData.clear()
        mData.addAll(list)
        mCount = mData.size
        this.invalidate()
    }

    fun getMemberCount() = mData.size

    fun setRemoveState(isRemove: Boolean) {
        this.isRemove = isRemove
    }

    override fun onDraw(canvas: Canvas) {

        //1.绘制背景
//        canvas.drawCircle(mCenter.toFloat(), mCenter.toFloat(), mCenter - paddingLeft / 2.toFloat(), mBgPaint)
        //2.绘制扇形
        //2.1设置每一个扇形的角度
        sweepAngle = if (mCount == 0) {
            360F
        } else {
            360F / mCount
        }


        startAngle = 270F - (sweepAngle / 2)
        //2.2设置扇形绘制的范围
        for (i in 0 until mCount) {
            mArcPaint.color = sectorColor[i]
            //sectorRectF 扇形绘制范围  startAngle 弧开始绘制角度 sweepAngle 每次绘制弧的角度
            // useCenter 是否连接圆心
            canvas.drawArc(sectorRectF, startAngle, sweepAngle, true, mArcPaint)
            //3.绘制文字
            drawTexts(canvas, mData[i].userId)
            //4.绘制图片
            if (mCount == 0) {
                continue
            }
            drawIcons(canvas, mData[i].bitmap)
            angles[i] = startAngle
//            Log.d(TAG, "onDraw: " + angles[i] + "     " + i)
            startAngle += sweepAngle
        }
        super.onDraw(canvas)
//        Log.d(TAG, "startAngle: $startAngle   sweepAngle  $sweepAngle")
    }

    /**
     * 以二分之一的半径的长度，扇形的一半作为图片的中心点
     * 图片的宽度为imageWidth
     *
     * @param canvas
     * @param mBitmap
     */
    private fun drawIcons(canvas: Canvas, mBitmap: Bitmap) {
        val imageWidth = mRadius / 9
        //计算半边扇形的角度 度=Math.PI/180 弧度=180/Math.PI,[决定图片圆心的位置，除以2则是在扇形的中间（横向）]
        val angle = ((startAngle + sweepAngle / 2) * Math.PI / 180).toFloat()
        //计算中心点的坐标
        val x = (mCenter + (mRadius / 3 * 2) * cos(angle.toDouble())).toFloat()
        val y = (mCenter + (mRadius / 3 * 2) * sin(angle.toDouble())).toFloat()
        //设置绘制图片的范围
        val rectF = RectF(x - imageWidth, y - imageWidth, x + imageWidth, y + imageWidth)
        canvas.drawBitmap(mBitmap.rotate(sweepAngle + startAngle), null, rectF, null)
    }

    /**
     * 使用path添加一个路径
     * 绘制文字的路径
     *
     * @param canvas 1
     * @param mString 2
     *
     * chengwf:我的项目不需要绘制文字，这个方法没太仔细看
     */
    private fun drawTexts(canvas: Canvas, mString: String) {
        val path = Path()
        //添加一个圆弧的路径
        path.addArc(sectorRectF, startAngle, sweepAngle)
        //测量文字的宽度
        val textWidth = mTextPaint.measureText(mString)
        //水平偏移
        val hOffset = (mRadius * 2 * Math.PI / mCount / 2 - textWidth / 2).toInt()
        //计算弧长 处理文字过长换行
        val l = (360 / mCount * Math.PI * mRadius / 180).toInt()
        if (textWidth > l * 4 / 5) {
//            val index = mString.length / 2
//            val startTextWidth = mTextPaint.measureText(mString.substring(0, index))
//            val endTextWidth = mTextPaint.measureText(mString.substring(index))
//            //水平偏移
////            hOffset = (mRadius * 2 * Math.PI / mCount / 2 - startTextWidth / 2).toInt()
//            val endHOffset = (mRadius * 2 * Math.PI / mCount / 2 - endTextWidth / 2).toInt()
//            //文字高度
//            val h = ((mTextPaint.ascent() + mTextPaint.descent()) * 1.5).toInt()
//
//            //根据路径绘制文字
//            //hOffset 水平的偏移量 vOffset 垂直的偏移量
////            canvas.drawTextOnPath(mString.substring(0, index), path, hOffset.toFloat(), mRadius / 6.toFloat(), mTextPaint)
//            canvas.drawTextOnPath(mString.substring(index), path, endHOffset.toFloat(), mRadius / 6 - h.toFloat(), mTextPaint)
        } else {
            //根据路径绘制文字
            canvas.drawTextOnPath(
                mString,
                path,
                hOffset.toFloat(),
                mRadius / 6.toFloat(),
                mTextPaint
            )

//            Log.d(TAG, "$mString -->hOffset == ${hOffset.toFloat()}")
        }
    }

    private var mPosition = 0

    /** 需要转动的角度 **/
    private var rotateA = 0F
    private var isRunning = false
    fun rotate(index: Int) {

        if (isRunning) return

        stateCallback(true)
        rotateA = sweepAngle * when {
            mPosition - index > 0 -> -(mCount - index).toFloat()

            mPosition - index == 0 -> index.toFloat()

            else -> (mCount + index).toFloat()
        }


        mPosition = index
//        rotateToPosition = 360F * 5.toFloat() - a

        val toDegree = 360f * 10 - rotateA

        Log.d(
            TAG,
            "start： a == $rotateA - sweepAngle == $sweepAngle - startAngle == $startAngle - toDegree == $toDegree"
        )
        val animator = ObjectAnimator.ofFloat(this@PieView, "rotation", 0f, toDegree).apply {
            duration = 2L.second()
            repeatCount = 0
            interpolator = DecelerateInterpolator()
            setAutoCancel(true)
        }
        animator.start()
        animator.addListener(AnimatorListen(end = {
            isRunning = false
            stateCallback(false)
            //指针指向的方向为270度
//            rotateToPosition = 270 - rotateToPosition
//            if (rotateToPosition < 0) {
//                rotateToPosition += 360
//            } else if (rotateToPosition == 0F) {
//                rotateToPosition = 270F
//            }
//            position = -Arrays.binarySearch(angles, rotateToPosition) - 1


            if (mData.isEmpty()) return@AnimatorListen

            Log.d(TAG, "end： a == $rotateA - sweepAngle == $sweepAngle - startAngle = $startAngle")
            if (isRemove) {
                mData.removeAt(mPosition)
            }
            refresh()
//            PanResult(mData[position - 1].userId).post()
        }))
    }

    companion object {
        private val TAG = PieView::class.java.simpleName
    }

    /**
     * 重置
     */
    fun reset() {
        stateCallback(true)
        val animator = ObjectAnimator.ofFloat(this@PieView, "rotation", 0f, 360F).apply {
            duration = 1L.second()
            repeatCount = 0
            interpolator = DecelerateInterpolator()
            setAutoCancel(true)
            addListener(AnimatorListen(end = {
                refresh()

                stateCallback(false)
            }))
        }
        animator.start()
        Log.d(TAG, "startAngle ：$startAngle  sweepAngle ：$sweepAngle")


        mCount = 0
//        sweepAngle = 0
        startAngle = 270F
//        mData.clear()
        refresh()
    }

    /***
     * 刷新
     */
    private fun refresh() {
        mCount = mData.size
        sweepAngle = if (mCount == 0) {
            0f
        } else {
            360F / mCount
        }
        this.invalidate()
    }

    /**
     * 复位
     */
    fun correction() {
        if (rotateA % 360 == 0F) {
            // 不需要复位
            return
        }

        stateCallback(true)
        // 复位角度是可以控制方向的

        // 复位旋转的方向
        val targetAngle = if (abs(rotateA) % 360F > 180) {
            0F
        } else {
            360F
        }

        ObjectAnimator.ofFloat(this@PieView, "rotation", 360F - rotateA % 360F, targetAngle)
            .apply {
                // 慢一点看的时候也明显一点
                duration = 3L.second()
                repeatCount = 0
                interpolator = DecelerateInterpolator()
                setAutoCancel(true)
                addListener(AnimatorListen(end = {

                    stateCallback(false)
                    mPosition = 0
                    startAngle = 270F
                    refresh()
                }))
            }.start()

        startAngle = 0F
    }

    /** running状态回调 **/
    private var stateCallback: (Boolean) -> Unit = {}

    fun addStateCallback(callback: (Boolean) -> Unit) {
        stateCallback = callback
    }
}