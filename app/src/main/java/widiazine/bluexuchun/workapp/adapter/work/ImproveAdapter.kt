package widiazine.bluexuchun.workapp.adapter.work

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import widiazine.bluexuchun.workapp.model.workModel.ImproveModel
import widiazine.bluexuchun.workapp.widget.workWidget.WorkImprove

class ImproveAdapter(
    val context:Context,
    var ImpoveLists:MutableList<ImproveModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ImproveViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImproveViewHolder(WorkImprove(context))
    }

    override fun getItemCount(): Int {
        return ImpoveLists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val improveItem = holder.itemView as WorkImprove
        improveItem.findView(ImpoveLists[position])
    }

}
