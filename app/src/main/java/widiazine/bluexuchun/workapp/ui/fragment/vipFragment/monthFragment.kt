package widiazine.bluexuchun.workapp.ui.fragment.vipFragment

import kotlinx.android.synthetic.main.fragment_vipweek.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.vip.VipAdapter
import widiazine.bluexuchun.workapp.model.VipModel.VipItemModel
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class monthFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_vipweek
    }

    override fun fragmentInit() {
        super.fragmentInit()
        var vipList = mutableListOf<VipItemModel>(
            VipItemModel(1, "5次批，5次改元", "10元"),
            VipItemModel(2, "10次批，10次改元", "13元"),
            VipItemModel(3, "15次批，15次改元", "16元")
        )
        week_item.adapter = VipAdapter(context, vipList)
    }

}