package widiazine.bluexuchun.workapp.adapter.collect

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.model.collectModel.GovermentModel
import widiazine.bluexuchun.workapp.ui.activity.Goverment.GoverDetailAcitivity
import widiazine.bluexuchun.workapp.widget.collectWidget.CollectGoverment

class GovermentAdapter(
    val context:Context,
    val goverListItems:MutableList<GovermentModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class goverListView(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return goverListView(CollectGoverment(context))
    }

    override fun getItemCount(): Int {
        return goverListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var goverListItem = holder.itemView as CollectGoverment

        goverListItem.findView(goverListItems[position])

        goverListItem.setOnClickListener {
            context.startActivity<GoverDetailAcitivity>()
        }
    }

}