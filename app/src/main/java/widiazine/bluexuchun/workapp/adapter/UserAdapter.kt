package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.UserInfoModel

class UserAdapter(var ctx:Context,var list:MutableList<UserInfoModel>):BaseAdapter(){


    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder
        var view:View

        view = View.inflate(ctx,R.layout.component_info,null)
        viewHolder = ViewHolder(view)
        view.tag = viewHolder

        val item = getItem(position) as UserInfoModel
        viewHolder.uname.text = item.username
        viewHolder.ulevel.text = item.userLevel
        viewHolder.uschool.text = item.userSchool
        return view
    }
}

class ViewHolder(var viewItem:View){
    var ava = viewItem.findViewById(R.id.userava) as ImageView
    var uname = viewItem.findViewById(R.id.username) as TextView
    var ulevel = viewItem.findViewById(R.id.level) as TextView
    var uschool = viewItem.findViewById(R.id.school) as TextView
}
