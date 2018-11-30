package widiazine.bluexuchun.workapp.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R

class SettingActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_settings
    }

    override fun init() {
        super.init()
        setImgsBar()
        header_title.text = "设置"
        header_back.visibility = View.VISIBLE
        header_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }

    }

    override fun specialSit(): Boolean {
        return false
    }

    override fun isBaseOnWidth(): Boolean {
        return false
    }

    override fun getSizeInDp(): Float {
        return 667F
    }

}