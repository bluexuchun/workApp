package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import widiazine.bluexuchun.workapp.contract.SplashContract
import widiazine.bluexuchun.workapp.model.db.DbControl

class SplashPresenter(val View:SplashContract.View):SplashContract.Presenter{
    override fun checkIsLogin(context: Context,dbName:String) {
        var Dbase = DbControl(context,dbName)
        var data = Dbase.selectAllInfo()
        var userInfo = data?.get(0)
        if(userInfo?.uid != null){
            // isLogined
            View.isLogined()
        }else{
            View.noLogin()
        }
    }
}