package com.application.customtimeapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import java.util.TimeZone


class ClockView @JvmOverloads constructor(private val context: Context?, private val attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    private var mHeight = 0
    private var mWidth = 0
    private var mRadius = 0
    private var mAngle = 0.0
    private var mCentreX = 0
    private var mCentreY = 0
    private var mPadding = 0
    private var mIsInit = false
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mNumbers: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private var mMinimum = 0
    private var mHour = 0
    private var mMinute = 0
    private var mSecond = 0
    private var mRect: Rect? = null
    private var mHourHandSize = 0
    private var mHandSize = 0
    private var timeZoneId: String? = ""
    private var isDarkMode: Boolean = true

    private fun init() {
        mHeight = 230
        mWidth = 230
        mCentreX = mWidth / 2
        mCentreY = mHeight / 2
        mMinimum = mHeight.coerceAtMost(mWidth)
        mRadius = mMinimum / 2 - mPadding
        mAngle = (Math.PI / 30 - Math.PI / 2).toFloat().toDouble()
        mPaint = Paint()
        mPath = Path()
        mRect = Rect()
        mHourHandSize = mRadius - mRadius / 2
        mHandSize = mRadius - mRadius / 6
        mIsInit = true
    }

    public fun setTimeZone(timeZone: String){
        this.timeZoneId = timeZone
    }

    public fun setIsDarkMode(isDarkMode: Boolean = false){
        this.isDarkMode = isDarkMode
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!mIsInit) {
            init()
        }
        drawCircle(canvas)
        drawHands(canvas)
       // drawNumerals(canvas)
        drawRedCircle(canvas)
        drawBlackCircle(canvas)
        postInvalidateDelayed(500)
    }

    private fun drawCircle(canvas: Canvas) {
        mPaint!!.reset()
        setPaintAttributes(if (isDarkMode) Color.BLACK else Color.WHITE, Paint.Style.FILL, 8)
        canvas.drawCircle(mCentreX.toFloat(), mCentreY.toFloat(), mRadius.toFloat(), mPaint!!)
    }

    private fun drawRedCircle(canvas: Canvas) {
        mPaint!!.reset()
        setPaintAttributes(Color.RED, Paint.Style.FILL, 8)
        canvas.drawCircle(mCentreX.toFloat(), mCentreY.toFloat(), 10.toFloat(), mPaint!!)
    }

    private fun drawBlackCircle(canvas: Canvas) {
        mPaint!!.reset()
        setPaintAttributes(if (isDarkMode) Color.WHITE else Color.BLACK, Paint.Style.STROKE, 4)
        canvas.drawCircle(mCentreX.toFloat(), mCentreY.toFloat(), 10.toFloat(), mPaint!!)
    }

    private fun setPaintAttributes(colour: Int, stroke: Paint.Style, strokeWidth: Int) {
        mPaint!!.reset()
        mPaint!!.color = colour
        mPaint!!.style = stroke
        mPaint!!.strokeWidth = strokeWidth.toFloat()
        mPaint!!.isAntiAlias = true
    }

    private fun drawHands(canvas: Canvas) {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone(this.timeZoneId))
        mHour = calendar.get(Calendar.HOUR_OF_DAY)
        mHour = if (mHour > 12) mHour - 12 else mHour
        mMinute = calendar.get(Calendar.MINUTE)
        mSecond = calendar.get(Calendar.SECOND)
        drawHourHand(canvas, (mHour + mMinute / 60.0) * 5f)
        drawMinuteHand(canvas, mMinute)
        drawSecondsHand(canvas, mSecond.toFloat())
    }

    private fun drawMinuteHand(canvas: Canvas, location: Int) {
        mPaint!!.reset();
        setPaintAttributes(if (isDarkMode) Color.WHITE else Color.BLACK, Paint.Style.STROKE,6);
        mAngle = Math.PI * location / 30 - Math.PI / 2;
        canvas.drawLine(mCentreX.toFloat(),
            mCentreY.toFloat(),
            (mCentreX + Math.cos( mAngle)* mHandSize).toFloat(),
            (mCentreY+Math.sin(mAngle)*mHourHandSize).toFloat(),
            mPaint!!);
    }

    private fun drawHourHand(canvas: Canvas, location: Double) {
        mPaint!!.reset()
        setPaintAttributes(if (isDarkMode) Color.WHITE else Color.BLACK, Paint.Style.STROKE, 6)
        mAngle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(
            mCentreX.toFloat(),
            mCentreY.toFloat(),
            (mCentreX + Math.cos(mAngle) * mHourHandSize).toFloat(),
            (mCentreY + Math.sin(mAngle) * mHourHandSize).toFloat(),
            mPaint!!
        )
    }

    private fun drawSecondsHand(canvas: Canvas, location: Float) {
        mPaint!!.reset()
        setPaintAttributes(Color.RED, Paint.Style.STROKE, 4)
        mAngle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(
            mCentreX.toFloat(),
            mCentreY.toFloat(),
            (mCentreX + Math.cos(mAngle) * mHandSize).toFloat(),
            (mCentreY + Math.sin(mAngle) * mHourHandSize).toFloat(),
            mPaint!!
        )
    }

  /*  private fun drawNumerals(canvas: Canvas) {
        mPaint!!.textSize = 50F
        mPaint!!.color = Color.BLACK
        for (number in mNumbers) {
            val num = number.toString()
            mPaint!!.getTextBounds(num, 0, num.length, mRect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (mCentreX + Math.cos(angle) * mRadius - mRect!!.width() / 2).toInt()
            val y = (mCentreY + Math.sin(angle) * mRadius + mRect!!.height() / 2).toInt()
            canvas.drawText(num, x.toFloat(), y.toFloat(), mPaint!!)
        }
    }*/



}
