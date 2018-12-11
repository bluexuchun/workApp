package widiazine.bluexuchun.workapp.ui.activity.AddChild

import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class NoregisterActivity: BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_settings
    }

    override fun init() {
        super.init()
        identifyBox.visibility = View.GONE
        setImgsBar()
        header_title.text = "信息填写"
        header_back.visibility = View.VISIBLE
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}