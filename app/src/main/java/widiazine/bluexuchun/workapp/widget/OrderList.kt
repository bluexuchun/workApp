package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.widget_orderlist.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.OrderModel.OrderlistModel
import widiazine.bluexuchun.workapp.utils.Utils
import java.net.URL


class OrderList(context: Context?, attrs: AttributeSet? = null) : LinearLayout(context, attrs){

    init {
        View.inflate(context, R.layout.widget_orderlist,this)
    }

    fun findView(orderListItem:OrderlistModel){
        // 配置图片
        if(orderListItem.order_img != null){
            val imgUrl = orderListItem.order_img

            val url = "${imgUrl}${Utils().getFormatTime()}"
            // 获取指定url返回的字节数组
            val bytes = URL(url).readBytes()
            // 把字节数组解码为位图数据
            var bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            order_img.setImageBitmap(bitmap)
        }
        order_money.setText(orderListItem.order_money)
        order_time.setText(orderListItem.order_time)
        /**
         * order_iscommet
         * true 已评价
         * false 未评价
         */
        if(orderListItem.order_iscommet){
            order_iscommet.setTextColor(resources.getColor(R.color.colorMain))
            order_iscommet.setText("已评价")
        }else{
            order_iscommet.setTextColor(resources.getColor(R.color.colorBlack))
            order_iscommet.setText("未评价")
        }

    }

}