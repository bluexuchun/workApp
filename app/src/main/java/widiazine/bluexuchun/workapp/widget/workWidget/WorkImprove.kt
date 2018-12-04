package widiazine.bluexuchun.workapp.widget.workWidget

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.widget_improvework.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.workModel.ImproveModel
import widiazine.bluexuchun.workapp.utils.Utils
import java.net.URL

class WorkImprove(context: Context?, attrs: AttributeSet? = null) : LinearLayout(context, attrs){

    init {
        View.inflate(context, R.layout.widget_improvework,this)
    }

    fun findView(hwListItem:ImproveModel){
        // 配置图片
        if(hwListItem.hwPic != null){
            val imgUrl = hwListItem.hwPic

            val url = "${imgUrl}${Utils().getFormatTime()}"
            // 获取指定url返回的字节数组
            val bytes = URL(url).readBytes()
            // 把字节数组解码为位图数据
            var bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            qtpic.setImageBitmap(bitmap)
        }

        //题目分类
        qttype.text = hwListItem.hwType


        //时间
        time.text = hwListItem.hwTime
    }
}