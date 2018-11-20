package widiazine.bluexuchun.workapp.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.RegisterContract
import widiazine.bluexuchun.workapp.presenter.RegisterPresenter
import widiazine.bluexuchun.workapp.widget.TextGradient


class RegisterActivity:BaseActivity(),RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun sendIng() {
        showProgress("正在发送中")
    }

    override fun registerIng() {
        showProgress("正在注册中...")
    }

    override fun sendSuccess() {
        hideProgress()
        toast("发送成功")
    }

    override fun sendFail() {
        toast("发送失败")
    }

    override fun registerSuccess() {
        hideProgress()
        toast("注册成功")
        startActivity<MainActivity>()
    }

    override fun regsiterFail() {
        toast("注册失败")
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun init() {
        super.init()
        /**
         * 设置状态栏的背景颜色
         * 以及字体颜色
         */
        setStatusBar(this.window,1,R.color.colorWhite)
        sendCode()
        register.setOnTouchListener { v, event ->
            isTouchView(event,listOf<View>(phone,password,repassword,code))
        }
    }

    private fun sendCode() {
        textGrad.onSectionChangeListener = object:TextGradient.OnSectionChangeListener{
            override fun sendCode() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
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