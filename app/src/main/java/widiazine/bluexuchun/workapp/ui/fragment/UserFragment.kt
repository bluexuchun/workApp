package widiazine.bluexuchun.workapp.ui.fragment

import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.activity.CollectActivity
import widiazine.bluexuchun.workapp.ui.activity.SettingActivity
import widiazine.bluexuchun.workapp.ui.activity.Goverment.GoverAcitivity
import widiazine.bluexuchun.workapp.ui.activity.OrderManage.OrderListActivity
import widiazine.bluexuchun.workapp.ui.activity.VipManage.VipActivity

class UserFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_user
    }

    override fun fragmentInit() {
        super.fragmentInit()

        // vip按钮
        vip_btn.setOnClickListener {
            startActivity<VipActivity>()
        }

        // 优惠券按钮

        // 订单按钮
        order_btn.setOnClickListener {
            startActivity<OrderListActivity>()
        }

        // 设置按钮
        mysettings.setOnClickListener {
            startActivity<SettingActivity>()
        }
        // 我的收藏
        mycollect.setOnClickListener {
            startActivity<CollectActivity>()
        }
        // 政策解读
        mygoverment.setOnClickListener {
            startActivity<GoverAcitivity>()
        }

    }

}