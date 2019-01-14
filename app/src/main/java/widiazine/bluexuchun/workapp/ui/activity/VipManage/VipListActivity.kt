package widiazine.bluexuchun.workapp.ui.activity.VipManage

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_viplist.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.vip.VipListAdapter
import widiazine.bluexuchun.workapp.model.VipModel.ViplistModel
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class VipListActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_viplist
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        val viplistitems = mutableListOf<ViplistModel>(
            ViplistModel(1, "购买月卡", "2019-1-10 12:32", "-10.00"),
            ViplistModel(2, "购买月卡", "2019-1-9 12:32", "-13.00"),
            ViplistModel(3, "购买周卡", "2019-1-8 12:32", "-15.00")
        )

        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE, Color.BLACK,"购买明细")
        header_black_back.visibility = View.VISIBLE

        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }

        viplist.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = VipListAdapter(context,viplistitems)
        }
    }

}