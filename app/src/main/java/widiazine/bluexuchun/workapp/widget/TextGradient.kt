package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import widiazine.bluexuchun.workapp.R

class TextGradient(context: Context) : TextView(context){

    var color1 = 0
    var color2 = 0

    constructor(context: Context,attributeSet: AttributeSet):this(context){
        val arrayType = context.obtainStyledAttributes(attributeSet, R.styleable.TextGradient)

        color1 = arrayType.getColor(R.styleable.TextGradient_color1,0)
        color2 = arrayType.getColor(R.styleable.TextGradient_color2,0)
        var test = Color.parseColor("#FDCC30")
        Log.d("color1","${color1}")
        Log.d("color","${test}")
    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if(changed){
            Log.d("color1","${color1}")
            paint.setShader(LinearGradient(0.toFloat(),0.toFloat(),measuredWidth.toFloat(),0.toFloat(), -144336,-144336,Shader.TileMode.CLAMP))
        }
    }
}