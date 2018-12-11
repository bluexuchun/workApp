package widiazine.bluexuchun.workapp.adapter.collect

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.model.collectModel.HomeWorkModel
import widiazine.bluexuchun.workapp.ui.activity.HomeWork.WorkDetailActivity
import widiazine.bluexuchun.workapp.widget.collectWidget.CollectHome

class HomeAdapter(
    val context: Context,
    val HomeListItems:MutableList<HomeWorkModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class HomeViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(CollectHome(context))
    }

    override fun getItemCount(): Int {
        return HomeListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeListItemView = holder.itemView as CollectHome
        homeListItemView.findView(HomeListItems[position])
        homeListItemView.setOnClickListener {
            context.startActivity<WorkDetailActivity>()
        }
    }

}
