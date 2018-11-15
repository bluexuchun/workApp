package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.TextView

open class TextGradient(context: Context, attrs: AttributeSet? = null) : TextView(context, attrs) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if(changed){
            paint.setShader(LinearGradient(0.toFloat(),0.toFloat(),measuredWidth.toFloat(),0.toFloat(), Color.parseColor("#FDCC30"),Color.parseColor("#FD9407"),Shader.TileMode.CLAMP))
        }
    }
}