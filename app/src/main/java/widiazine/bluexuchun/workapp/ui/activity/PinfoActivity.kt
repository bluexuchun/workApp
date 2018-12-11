package widiazine.bluexuchun.workapp.ui.activity

import android.util.Log
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_pinfo.*
import org.jetbrains.anko.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.UserAdapter
import widiazine.bluexuchun.workapp.model.UserInfoModel

class PinfoActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_pinfo
    }

    override fun init() {
        super.init()
        var userAry = mutableListOf<UserInfoModel>(
            UserInfoModel(null,"孙金鹏","小学","皮皮第一小学"),
            UserInfoModel(null,"孙东波","大学","皮皮第一大学")
        )
        infolist.apply {
            adapter = UserAdapter(context,userAry)
        }

        setListViewHeight(infolist)

        var viewList = listOf<View>(username)
        svBox.setOnTouchListener { v, event ->
            isTouchView(event,viewList)
            false
        }
        infolist.setOnTouchListener { v, event ->
            isTouchView(event,viewList)
            true
        }
        isRegister.setOnClickListener {
            alert("您的孩子是否已被注册"){
                positiveButton("是"){
                    toast("yes")
                }
                negativeButton("否"){
                    toast("no")
                }
            }.show()
        }

        submit.setOnClickListener {

        }
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    /**
     * 重新定义ListView的高度
     */
    fun setListViewHeight(listview:ListView){
        var listAdapter = listview.adapter
        var totalHeight = 0
        for (i in 0..(listAdapter.count - 1)){
            var listItem = listAdapter.getView(i,null,listview)
            Log.v("height","${listItem.measuredHeight}")
            listItem.measure(0,0)
            Log.v("height","${listItem.measuredHeight}")
            totalHeight += listItem.measuredHeight
        }
        var params = listview.layoutParams
        params.height = totalHeight + (listview.dividerHeight * ((listAdapter.count) - 1))
        listview.layoutParams = params
    }
}

