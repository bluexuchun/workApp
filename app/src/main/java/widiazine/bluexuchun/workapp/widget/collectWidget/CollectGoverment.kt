package widiazine.bluexuchun.workapp.widget.collectWidget

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.widget_collectgoverment.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.collectModel.GovermentModel
import widiazine.bluexuchun.workapp.utils.Utils
import java.net.URL

class CollectGoverment(context: Context?, attrs: AttributeSet? = null) : LinearLayout(context, attrs){

    init {
        View.inflate(context, R.layout.widget_collectgoverment,this)
    }

    fun findView(goverListItem:GovermentModel){
        // 配置图片
        if(goverListItem.goverimg != null){
            val imgUrl = goverListItem.goverimg

            val url = "${imgUrl}${Utils().getFormatTime()}"
            // 获取指定url返回的字节数组
            val bytes = URL(url).readBytes()
            // 把字节数组解码为位图数据
            var bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            goverpic.setImageBitmap(bitmap)
        }

        // 加载标题
        govertitle.text = goverListItem.govertitle

        // 加载内容
        govercontent.text = goverListItem.govercontent

        // 加载时间
        govertime.text = goverListItem.govertime

        // 加载访问数量
        governums.text = goverListItem.governums

    }

}