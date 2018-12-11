package widiazine.bluexuchun.workapp.ui.activity

import android.Manifest
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.LoginContract

class LoginActivity:BaseActivity(),LoginContract.View{

    private var rxPermissions = RxPermissions(this)
    override fun specialSit(): Boolean {
        return true
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()
        // 初始的权限申请 读写的权限和网络的权限
        rxPermissions
            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
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
        /**
         * 点击空白的地方 隐藏软键盘
         */
        allTouch.setOnTouchListener { v, event ->
            isTouchView(event, listOf<View>(phone,password))
        }
        btn_register.setOnClickListener {
            startActivity<RegisterActivity>()
        }
        /**
         * 点击skip 跳到首页
         */
        skip_btn.setOnClickListener {
            startActivity<MainActivity>()
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