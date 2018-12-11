package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import widiazine.bluexuchun.workapp.model.ChildModel
import widiazine.bluexuchun.workapp.widget.ChildSearch

class ChildSearchAdapter(
    var context: Context,
    var childList:MutableList<ChildModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ChildViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChildViewHolder(ChildSearch(context))
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var childItem = holder.itemView as ChildSearch
        childItem.findView(childList[position])
        childItem.setOnClickListener {

            for (i in 0..childList.size - 1){
                childList[i].ischoose = false
            }
            childList[position].ischoose = true
            notifyDataSetChanged()
        }
    }

}