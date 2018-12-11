package widiazine.bluexuchun.workapp.ui.activity.AddChild

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_isresgister.*
import kotlinx.android.synthetic.main.component_child.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.ChildSearchAdapter
import widiazine.bluexuchun.workapp.contract.IsregisterContract
import widiazine.bluexuchun.workapp.model.ChildModel
import widiazine.bluexuchun.workapp.presenter.IsregisterPresenter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class IsregisterActivity:BaseActivity(),IsregisterContract.view{

    val presenter = IsregisterPresenter(this)

    override fun searchSuccess() {
        toast(R.string.searchSuccess)
        childRv.adapter?.notifyDataSetChanged()
    }

    override fun searchFail() {
        toast(R.string.searchFail)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_isresgister
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        setImgsBar()
        header_title.text = "信息填写"
        header_back.visibility = View.VISIBLE


        childRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ChildSearchAdapter(context,presenter.searchList)
        }

        bodylayout.setOnTouchListener { v, event ->
            isTouchView(event, listOf<View>(editlayout))
            false
        }
        childRv.setOnTouchListener { v, event ->
            isTouchView(event, listOf<View>(editlayout))
            false
        }

        /**
         * 搜索结果
         */
        btn_search.setOnClickListener {
            hideKeyboard()
            var key = editinfo.text.toString().trim()
            if(key.isEmpty()){
                toast(R.string.key_tips)
            }else{
                presenter.searhInfo(key)
            }
        }

        submit.setOnClickListener {
            var chooseId = -1
            for (i in 0..presenter.searchList.size - 1){
                Log.v("item","${presenter.searchList[i].ischoose}")
            }
        }

    }

}