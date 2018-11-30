package widiazine.bluexuchun.workapp.ui.activity

import android.Manifest
import android.database.sqlite.SQLiteDatabase
import android.os.CountDownTimer
import android.os.Message
import android.util.Log
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.RegisterContract
import widiazine.bluexuchun.workapp.model.db.DataBaseHelper
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.model.db.database
import widiazine.bluexuchun.workapp.presenter.RegisterPresenter
import widiazine.bluexuchun.workapp.utils.Utils


class RegisterActivity:BaseActivity(),RegisterContract.View{

    private val presenter = RegisterPresenter(this)

    private var rxPermissions = RxPermissions(this)

    private var isCount = false

    override fun registerIng() {
        showProgress("正在注册中...")
    }

    override fun sendSuccess() {
        toast("发送成功")
        isCount = true
        var mCount = object : CountDownTimer(60000,1000){
            override fun onFinish() {
                textGrad.text = "获取验证码"
                isCount = false
            }

            override fun onTick(millisUntilFinished: Long) {
                textGrad.text = "重新发送${millisUntilFinished/1000}s"
            }
        }
        mCount.start()
    }

    override fun sendFail() {
        toast("发送失败")
    }

    override fun registerSuccess() {
        hideProgress()
        toast("注册成功")
        startActivity<MainActivity>()
    }

    override fun regsiterFail(message: String) {
        toast(message)
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

        // 初始的权限申请
        rxPermissions
            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET)
            .subscribe(){
                if(it.granted){
                    // 权限通过
                }else if(it.shouldShowRequestPermissionRationale){
                    // 权限拒绝 但不是再也不显示
                    toast("Permission Denied")
                }else{
                    // 权限拒绝并再也不显示
                    toast("Permission Denied")
                }
            }
        register.setOnTouchListener { v, event ->
            isTouchView(event,listOf<View>(phone,password,repassword,code))
        }

        /**
         * 注册
         */
        btn_register.setOnClickListener {
            sendForm("register")
        }
        /**
         * 获取验证码
         */
        textGrad.setOnClickListener {
            if(!isCount){
                rxPermissions
                    .request(Manifest.permission.SEND_SMS)
                    .subscribe(){
                        if(it){
                            sendForm("code")
                        }else{
                            toast("Permission Denied")
                        }
                    }
            }
        }

        /**
         * 返回
         */
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }


//        textGrad.onSectionChangeListener = object :TextGradient.OnSectionChangeListener{
//            override fun sendCode() {
//                Log.v("123","123")
//
//            }
//        }
    }

    private fun sendForm(type:String){
        var phoneNumber = phone.text.toString().trim()
        var passwordText = password.text.toString().trim()
        var rePasswordText = repassword.text.toString().trim()
        var codeText = code.text.toString().trim()
        var utils = Utils()
        if(type == "code"){
            if(utils.isEmpty(phoneNumber)){
                if(phoneNumber.length == 11){
                    presenter.sendCode(phoneNumber)
                }else{
                    toast("请输入正确的手机号码")
                }
            }else{
                toast("请输入手机号")
            }
        }else if(type == "register"){
            if(utils.isEmpty(phoneNumber)){
                if(utils.isEmpty(passwordText)){
                    if(utils.isEmpty(rePasswordText)){
                        if(passwordText == rePasswordText){
                            if(utils.isEmpty(codeText)){
                                presenter.register(phoneNumber,passwordText,codeText,applicationContext)
                            }else{
                                toast("请输入验证码")
                            }
                        }else{
                            toast("两次输入的密码不一致")
                        }
                    }else{
                        toast("请输入确认密码")
                    }
                }else{
                    toast("请输入密码")
                }
            }else{
                toast("请输入手机号")
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