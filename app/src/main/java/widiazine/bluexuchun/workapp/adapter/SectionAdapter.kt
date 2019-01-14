package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.component_section.view.*
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.SectionModel
import widiazine.bluexuchun.workapp.ui.activity.UploadDetailActivity
import widiazine.bluexuchun.workapp.utils.Preference

class SectionAdapter(
    var context: Context,
    var listView:MutableList<SectionModel>
): PagerAdapter(){

    private var type by Preference(context,"type",0)

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as LinearLayout
    }

    // 数量
    override fun getCount(): Int {
        return listView.size
    }

    // 删除页卡
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    // 实例化页卡
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var viewItem = inflator.inflate(R.layout.component_section,container,false)
        viewItem.sectionName.text = listView[position].sectionName
        container.addView(viewItem)

        viewItem.setOnClickListener {
            Log.v("sectionId","${listView[position].sectionId}")
            Log.v("type","${type}")
            Log.v("sectionName","${it.sectionName}")
            context.startActivity<UploadDetailActivity>()
        }
        return viewItem
    }
}
