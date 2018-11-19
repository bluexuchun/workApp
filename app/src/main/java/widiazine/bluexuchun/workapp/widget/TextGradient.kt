package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import widiazine.bluexuchun.workapp.R

class TextGradient(context: Context) : TextView(context){

    var color1 = "#fdcc30"
    var color2 = "#fd9407"

    constructor(context: Context,attributeSet: AttributeSet):this(context){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var mPaint = paint
        var mText = text.toString()
        var mTextBound = Rect()
        mPaint.getTextBounds(mText,0,mText.length,mTextBound)
        var mLinearGradient = LinearGradient(0.toFloat(),0.toFloat(),measuredWidth.toFloat(),0.toFloat(), Color.parseColor(color1),Color.parseColor(color2),Shader.TileMode.CLAMP)
        mPaint.setShader(mLinearGradient)
        /**
         * 文字居中
         */
        mPaint.textAlign = Paint.Align.CENTER
        /**
         * 垂直居中
         */
        var FontMetrices = paint.getFontMetricsInt()
        var baseline = (top + measuredHeight - FontMetrices.bottom - FontMetrices.top) / 2
        canvas?.drawText("取消",((measuredWidth - left)/2).toFloat(),baseline.toFloat(), mPaint)
    }
}