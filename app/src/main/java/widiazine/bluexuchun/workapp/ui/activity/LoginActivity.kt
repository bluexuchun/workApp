package widiazine.bluexuchun.workapp.ui.activity

import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.LoginContract

class LoginActivity:BaseActivity(),LoginContract.View{
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()
        /**
         * 设置全屏
         */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
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


}