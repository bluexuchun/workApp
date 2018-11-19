package widiazine.bluexuchun.workapp.ui.activity

import widiazine.bluexuchun.workapp.R

class RegisterActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
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