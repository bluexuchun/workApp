package widiazine.bluexuchun.workapp.adapter.vip

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.VipModel.VipItemModel
import widiazine.bluexuchun.workapp.ui.activity.VipManage.VipDetailActivity

class VipAdapter(var context: Context?, var vipLists:MutableList<VipItemModel>):BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var viewbox = inflator.inflate(R.layout.widget_vipitem,parent,false)
        var vip_id = viewbox.findViewById<TextView>(R.id.vip_id)
        var vip_title = viewbox.findViewById<TextView>(R.id.vip_title)
        var vip_price = viewbox.findViewById<TextView>(R.id.vip_price)

        var vipinfo = vipLists.get(position)
        Log.v("item","${vipinfo}")
        vip_id.text = vipinfo.id.toString()
        vip_title.text = vipinfo.title
        vip_price.text = vipinfo.price

        viewbox.setOnClickListener {
            var vipid = vip_id.text
            parent!!.context.startActivity<VipDetailActivity>("vipid" to vipid)
        }

        return viewbox
    }

    override fun getItem(position: Int): Any {
        return vipLists.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return vipLists.size
    }
}
