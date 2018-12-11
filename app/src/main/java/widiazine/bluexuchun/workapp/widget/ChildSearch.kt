package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.component_child.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.ChildModel
import widiazine.bluexuchun.workapp.utils.Utils
import java.net.URL

class ChildSearch(context: Context?, attrs: AttributeSet? = null) : LinearLayout(context, attrs){
    init {
        View.inflate(context, R.layout.component_child,this)
    }

    fun findView(childList:ChildModel){
        // 配置图片
        if(childList.userava != null){
            val imgUrl = childList.userava

            val url = "${imgUrl}${Utils().getFormatTime()}"
            // 获取指定url返回的字节数组
            val bytes = URL(url).readBytes()
            // 把字节数组解码为位图数据
            var bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            userava.setImageBitmap(bitmap)
        }

        username.text = childList.username
        level.text = childList.userlevel
        school.text = childList.userschool
        if(childList.ischoose){
            choosebtn.setImageResource(R.mipmap.choose)
        }else{
            choosebtn.setImageResource(R.mipmap.nochoose)
        }
    }
}