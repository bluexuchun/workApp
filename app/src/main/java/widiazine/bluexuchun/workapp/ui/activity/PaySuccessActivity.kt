package widiazine.bluexuchun.workapp.ui.activity

import android.graphics.Color
import widiazine.bluexuchun.workapp.R

class PaySuccessActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_paysuccess
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE, Color.BLACK,"购买成功")
    }

}