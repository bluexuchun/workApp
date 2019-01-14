package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import widiazine.bluexuchun.workapp.model.OrderModel.OrderlistModel
import widiazine.bluexuchun.workapp.widget.OrderList

class OrderlistAdapter(
    val context: Context,
    val orderlists:MutableList<OrderlistModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class OrderView(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderView(OrderList(context))
    }

    override fun getItemCount(): Int {
        return orderlists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var orderItem = holder.itemView as OrderList

        orderItem.findView(orderlists[position])


    }

}