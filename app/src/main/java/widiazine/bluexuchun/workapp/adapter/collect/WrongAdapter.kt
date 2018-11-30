package widiazine.bluexuchun.workapp.adapter.collect

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import widiazine.bluexuchun.workapp.model.collectModel.WrongModel
import widiazine.bluexuchun.workapp.widget.collectWidget.CollectWrong

class WrongAdapter(
    val context: Context,
    var wrongListItems:MutableList<WrongModel>
    ):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class wrongListView(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /**
         * wrongListView(itemView)
         * 代表着这个itemView 以什么形式展现
         * 理所当然以CollectWrong的形式展现
         */
        return wrongListView(CollectWrong(context))
    }

    override fun getItemCount(): Int {
        return wrongListItems.size
    }

    /**
     * 数据的绑定
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var wrongListItem = holder.itemView as CollectWrong

        /**
         * 将数据渲染到CollectWrong里
         */
        wrongListItem.findView(wrongListItems[position])
    }

}