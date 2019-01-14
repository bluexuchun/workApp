package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import widiazine.bluexuchun.workapp.contract.CidentifyContract
import widiazine.bluexuchun.workapp.model.ResponseCommon
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.utils.Urls

class CidentifyPresenter(val view: CidentifyContract.View):CidentifyContract.Presenter{
    override fun choose(id:Int,type: String, applicationContext: Context) {
        var url = "${Urls().SERVER}api.php?entry=app&c=personal&a=personal&do=idt"
        OkGo.post<String>(url)
            .tag(this)
            .isSpliceUrl(true)
            .params("uid",id)
            .params("role_type",type)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var Dbase = DbControl(applicationContext, UserTables.NAME)
                    var gson = Gson()
                    var responseData = gson.fromJson(response?.body(),ResponseCommon::class.java)
                    if(responseData.status == 1){
                        var utype = "child"
                        if(type == "2"){
                            utype == "parent"
                        }else{
                            utype == "child"
                        }
                        Dbase.updateInfo(id,"role_type" to type)
                        Log.v("id","${id}")
                        view.chooseSuccess(utype,responseData.message,id)
                    }
                }
            })
    }
}