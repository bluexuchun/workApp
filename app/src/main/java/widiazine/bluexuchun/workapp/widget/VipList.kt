package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.component_vipitem.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.VipModel.ViplistModel

class VipList(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){
    init {
        View.inflate(context, R.layout.component_vipitem,this)
    }

    fun findView(viplistitem: ViplistModel){
        viptext.text = viplistitem.title
        viptime.text = viplistitem.time
        vippay.text = viplistitem.pay
    }

}