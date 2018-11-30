package widiazine.bluexuchun.workapp.ui.fragment

import android.util.Log
import kotlinx.android.synthetic.main.fragment_home.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.TagsAdapter
import widiazine.bluexuchun.workapp.model.TagsModel

class MainFragment:BaseFragment(){

    val list = mutableListOf<TagsModel>()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun fragmentInit() {
        list.clear()
        for (i in 0..50) {
            var users = TagsModel("施${i}号","#ffffff")
            list.add(users)
        }
        Log.v("list","${list}")
        var tagList = TagsAdapter(list)
        tags.setAdapter(tagList)
    }

}