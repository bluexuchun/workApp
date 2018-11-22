package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.*
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import widiazine.bluexuchun.workapp.R

class TextGradient:TextView {

//    var onSectionChangeListener:OnSectionChangeListener? = null

    private var isCount = false

    private var color1 = Color.parseColor("#ffd94f")
        set(value) {
            field = value
            invalidate()
        }
    private var color2 = Color.parseColor("#ffad42")
        set(value) {
            field = value
            invalidate()
        }

    @JvmOverloads
    constructor(context: Context):this(context,null){

    }

    @JvmOverloads
    constructor(context:Context,attrs:AttributeSet?):this(context,attrs,0){

    }
    @JvmOverloads
    constructor(context:Context,attrs:AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        init(context,attrs,defStyleAttr)
    }

    private fun init(context: Context,attrs: AttributeSet?,defStyleAttr: Int) {
        var arrayType = context.obtainStyledAttributes(attrs,R.styleable.TextGradient)

        color1 = arrayType.getColor(R.styleable.TextGradient_color1,color1)
        color2 = arrayType.getColor(R.styleable.TextGradient_color2,color2)

        arrayType.recycle()
    }


//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        var mPaint = paint
//        var FontMetrices = paint.getFontMetricsInt()
//        var mText = text.toString().trim()
//        var mTextBound = Rect()
//        mPaint.getTextBounds(mText,0,mText.length,mTextBound)
//        var mLinearGradient = LinearGradient(0f,(0 + FontMetrices.leading).toFloat(),0f,(measuredHeight - FontMetrices.descent).toFloat(), color1,color2,Shader.TileMode.CLAMP)
//        mPaint.setShader(mLinearGradient)
//        /**
//         * 文字居中
//         */
//        mPaint.textAlign = Paint.Align.CENTER
//        /**
//         * 垂直居中
//         */
//        var baseline = (top + measuredHeight - FontMetrices.bottom - FontMetrices.top) / 2
//        canvas?.drawText(mText,((measuredWidth - left)/2).toFloat(),baseline.toFloat(), mPaint)
//    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        var mPaint = paint
        var FontMetrices = paint.getFontMetricsInt()
        var mLinearGradient = LinearGradient(0f,(0 + FontMetrices.leading).toFloat(),0f,(measuredHeight - FontMetrices.descent).toFloat(), color1,color2,Shader.TileMode.CLAMP)
        mPaint.setShader(mLinearGradient)
    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when(event.action){
//            MotionEvent.ACTION_DOWN -> {
//                /**
//                 * 实现倒计时
//                 */
//                 if(!isCount){
//                     var mCount = object :CountDownTimer(60000,1000){
//                         override fun onFinish() {
//                             isCount = false
//                             textWord = "获取验证码"
//                         }
//
//                         override fun onTick(millisUntilFinished: Long) {
//                             isCount = true
//                             textWord = "重新获取${millisUntilFinished/1000}s"
//                         }
//                     }
//                     mCount.start()
//                 }
//            }
//        }
//        return true
//    }

//    interface OnSectionChangeListener{
//        fun sendCode()
//    }
}