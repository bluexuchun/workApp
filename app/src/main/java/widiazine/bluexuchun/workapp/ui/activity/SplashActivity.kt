package widiazine.bluexuchun.workapp.ui.activity

import android.os.Handler
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.SplashContract
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.presenter.SplashPresenter

class SplashActivity:BaseActivity(),SplashContract.View{

    val presenter = SplashPresenter(this)

    val handler by lazy{
        Handler()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        super.init()
        // 检查是否登录
        presenter.checkIsLogin(applicationContext,UserTables.NAME)
    }

    override fun specialSit(): Boolean = true

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun isLogined() {
        handler.postDelayed({
            startActivity<MainActivity>()
            finish()
        },2000)

    }

    override fun noLogin() {
        toast(R.string.no_login)
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },2000)
    }

}