package widiazine.bluexuchun.workapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.component_section.view.*
import kotlinx.android.synthetic.main.fragment_upload.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.SectionAdapter
import widiazine.bluexuchun.workapp.model.SectionModel

class UploadFragment:BaseFragment(){

    override fun getLayoutResId(): Int {
        return R.layout.fragment_upload
    }

    override fun fragmentInit() {
        super.fragmentInit()
        initViewItem()
    }

    private fun initViewItem(){
        // 加载多少科目 循环多少次 并把科目的头像和名字传入
        // 假设有3个科目
        var sectionList = mutableListOf<SectionModel>(
            SectionModel(null,"语文"),
            SectionModel(null,"数学"),
            SectionModel(null,"英文")
        )

        uploadvp.apply {
            adapter = SectionAdapter(context,sectionList)
        }

    }

}