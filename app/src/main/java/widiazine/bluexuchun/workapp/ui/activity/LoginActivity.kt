package widiazine.bluexuchun.workapp.ui.activity

import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.LoginContract

class LoginActivity:BaseActivity(),LoginContract.View{
    override fun specialSit(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()
        /**
         * 点击空白的地方 隐藏软键盘
         */
        allTouch.setOnTouchListener { v, event ->
            isTouchView(event, listOf<View>(phone,password))
        }
        btn_register.setOnClickListener {
            startActivity<RegisterActivity>()
        }

    }

    /**
     * 登陆成功
     */
    override fun onLoadSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 登陆失败
     */
    override fun onLoadFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBaseOnWidth(): Boolean {
        return false
    }

    override fun getSizeInDp(): Float {
        return 667.toFloat()
    }


}