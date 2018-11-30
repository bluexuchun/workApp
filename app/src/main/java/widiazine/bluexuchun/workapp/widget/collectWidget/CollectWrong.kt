package widiazine.bluexuchun.workapp.widget.collectWidget

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.widget_collectwrong.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.collectModel.WrongModel
import widiazine.bluexuchun.workapp.utils.Utils
import java.net.URL

class CollectWrong(context: Context?, attrs: AttributeSet? = null) : LinearLayout(context, attrs){
    init {
        View.inflate(context, R.layout.widget_collectwrong,this)
    }

    /**
     * 加载数据的模型
     * 数据列表的内容渲染
     */
    fun findView(wrongListItem:WrongModel){
        // 配置图片
        if(wrongListItem.wrongPic != null){
            val imgUrl = wrongListItem.wrongPic

            val url = "${imgUrl}${Utils().getFormatTime()}"
            // 获取指定url返回的字节数组
            val bytes = URL(url).readBytes()
            // 把字节数组解码为位图数据
            var bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            qtpic.setImageBitmap(bitmap)
        }

        //配置正确或错误的图片隐藏和显示
        if(wrongListItem.wrongCurrect!!){
            statusInt.visibility = View.GONE
        }else{
            statusInt.visibility = View.VISIBLE
        }

        //状态
        when(wrongListItem.wrongStatus){
            // 已检查
            1 -> {
                statusIcon.setImageResource(R.mipmap.ischeck)
                qtStatus.text = "已检查"
            }
            2 -> {
                statusIcon.setImageResource(R.mipmap.nocheck)
                qtStatus.text = "未检查"
            }
            3 -> {
                statusIcon.setImageResource(R.mipmap.isqt)
                qtStatus.text = "已解答"
            }
            4 -> {
                statusIcon.setImageResource(R.mipmap.noqt)
                qtStatus.text = "未解答"
            }
        }

        //时间
        time.text = wrongListItem.wrongTime
    }
}