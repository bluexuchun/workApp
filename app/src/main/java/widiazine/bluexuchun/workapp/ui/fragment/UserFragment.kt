package widiazine.bluexuchun.workapp.ui.fragment

import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.activity.CollectActivity
import widiazine.bluexuchun.workapp.ui.activity.SettingActivity
import widiazine.bluexuchun.workapp.ui.activity.Goverment.GoverAcitivity

class UserFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_user
    }

    override fun fragmentInit() {
        super.fragmentInit()
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