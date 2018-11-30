package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.moxun.tagcloudlib.view.TagsAdapter
import kotlinx.android.synthetic.main.component_tag.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.model.TagsModel

/**
 * 3D云标签 适配器
 */
class TagsAdapter(val list: MutableList<TagsModel>) : TagsAdapter() {

    val mList = list


    /**
     * 针对每个Tag返回一个权重值，该值与ThemeColor和Tag初始大小有关；一个简单的权重值生成方式是对一个数N取余或使用随机数
     */
    override fun getPopularity(position: Int): Int {
        return 1
    }

    /**
     * Tag主题色发生变化时会回调该方法
     */
    override fun onThemeColorChanged(view: View?, themeColor: Int) {

    }

    /**
     * 返回每个Tag实例
     */
    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
        var tag = View.inflate(context,R.layout.component_tag,null)
        tag.tagName.text = mList[position].username
        var tagBack = tag.tagColor.background as GradientDrawable
        tagBack.setColor(Color.parseColor(mList[position].color))

        return tag
    }

    /**
     * 返回Tag数据
     */
    override fun getItem(position: Int): Any {

        return mList[position]
    }

    /**
     * 返回Tag数量
     */
    override fun getCount(): Int {
        return mList.size
    }

}
