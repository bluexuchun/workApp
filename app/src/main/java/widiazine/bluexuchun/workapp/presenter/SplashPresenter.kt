package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import android.util.Log
import widiazine.bluexuchun.workapp.contract.SplashContract
import widiazine.bluexuchun.workapp.model.db.DbControl

class SplashPresenter(val View:SplashContract.View):SplashContract.Presenter{
    override fun checkIsLogin(context: Context,dbName:String) {
        var Dbase = DbControl(context,dbName)
        var data = Dbase.selectAllInfo()
        Log.v("data","${data?.size}")
        if(data?.size!! > 0){
            var userInfo = data?.get(0)
            View.isLogined()
        }else{
            View.noLogin()
        }
    }
}