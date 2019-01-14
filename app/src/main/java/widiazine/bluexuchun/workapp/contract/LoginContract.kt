package widiazine.bluexuchun.workapp.contract

import android.content.Context

interface LoginContract{
    interface Presenter:BasePresenter{
        /**
         * 登陆方法
         */
        fun login(
            username:String,
            password:String,
            applicationContext: Context
        )
    }
    interface View{
        /**
         * 登陆成功
         * type 角色分类
         * uid 用户Id
         */
        fun onLoadSuccess(type:String,uid:Int?)
        /**
         * 登陆失败
         */
        fun onLoadFail(message:String)
    }
}