package widiazine.bluexuchun.workapp.contract

import android.content.Context

interface SplashContract{
    interface Presenter:BasePresenter{
        /**
         * 检查是否登录
         */
        fun checkIsLogin(context: Context,dbName:String)
    }

    interface View{
        /**
         * 已经登录
         */
        fun isLogined()
        /**
         * 未登录
         */
        fun noLogin()
    }
}