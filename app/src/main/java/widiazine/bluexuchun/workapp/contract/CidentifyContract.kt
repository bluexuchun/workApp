package widiazine.bluexuchun.workapp.contract

import android.content.Context

interface CidentifyContract{
    interface Presenter:BasePresenter{
        /**
         * 选择身份
         *
         * type == 1是学生
         * type == 2是家长
         */
        fun choose(id:Int,type:String,applicationContext: Context)
    }
    interface View{
        /**
         * 选择成功
         * type == child
         * type == parent
         * uid
         */
        fun chooseSuccess(type:String,message:String,uid:Int)
    }
}