package widiazine.bluexuchun.workapp.adapter.vip

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import widiazine.bluexuchun.workapp.model.VipModel.ViplistModel
import widiazine.bluexuchun.workapp.widget.VipList

class VipListAdapter(
    var context:Context,
    var viplists:MutableList<ViplistModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class VipViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VipViewHolder(VipList(context))
    }

    override fun getItemCount(): Int {
        return viplists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var vipitem = holder.itemView as VipList
        vipitem.findView(viplists[position])
    }

}