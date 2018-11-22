package widiazine.bluexuchun.workapp.contract

import android.content.Context

interface RegisterContract{
    interface Presenter:BasePresenter{
        // 发送验证码
        fun sendCode(phone:String)
        // 注册
        fun register(
            phone: String,
            password: String,
            code: String,
            applicationContext: Context
        )
    }
    interface View{
        // 发送验证码成功
        fun sendSuccess()
        // 发送验证码失败
        fun sendFail()
        // 注册中
        fun registerIng()
        // 注册成功
        fun registerSuccess()
        // 注册失败
        fun regsiterFail(message:String)
    }
}