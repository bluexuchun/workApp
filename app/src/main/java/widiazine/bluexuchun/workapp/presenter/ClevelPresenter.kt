package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import widiazine.bluexuchun.workapp.contract.ClevelContract
import widiazine.bluexuchun.workapp.model.ResponseCommon
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.utils.Urls

class ClevelPresenter(val view:ClevelContract.View):ClevelContract.Presenter{

    override fun chooseLevel(type: String,uid:Int,applicationContext:Context) {
        var url = "${Urls().SERVER}api.php?entry=app&c=personal&a=personal&do=show_grade"
        OkGo.post<String>(url)
            .tag(this)
            .isSpliceUrl(true)
            .params("uid",uid)
            .params("gid",type)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var Dbase = DbControl(applicationContext, UserTables.NAME)
                    var gson = Gson()
                    var responseData = gson.fromJson(response?.body(), ResponseCommon::class.java)
                    Log.v("responseData","${responseData}")
                    Log.v("id","${uid}")
//                    if(responseData.status == 1){
//                        Dbase.updateInfo(uid,"gid" to type)
//                        view.chooseSuccess(responseData.message,uid)
//                    }
                }
            })
    }

}