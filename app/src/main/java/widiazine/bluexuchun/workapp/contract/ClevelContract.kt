package widiazine.bluexuchun.workapp.contract

import android.content.Context

interface ClevelContract{
    interface Presenter:BasePresenter{
        /**
         * 选择年级 gid
         * type
         * -1 学前
         * -2 小学
         * -3 初中
         * -4 高中
         */
        fun chooseLevel(type:String,uid:Int,applicationContext: Context)
    }

    interface View{
        /**
         * 选择成功
         */
        fun chooseSuccess(message:String,uid:Int)
    }
}