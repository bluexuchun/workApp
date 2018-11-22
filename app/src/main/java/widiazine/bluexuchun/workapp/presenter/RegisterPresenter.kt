package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import widiazine.bluexuchun.workapp.contract.RegisterContract
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.model.ResponseModel
import widiazine.bluexuchun.workapp.utils.Urls

class RegisterPresenter(val View:RegisterContract.View):RegisterContract.Presenter{
    override fun sendCode(phoneNumber: String) {

//        var params = HashMap<String, String>()
        var url = "${Urls().SERVER}api.php?entry=app&c=user&a=sendSms"
//        params.put("phone",phoneNumber)
//        var jsonObject = JSONObject(params)
        OkGo.post<String>(url)
            .tag(this)
            .isSpliceUrl(true)
            .params("phone",phoneNumber)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    View.sendSuccess()
                }
            })
    }

    override fun register(
        phone: String,
        password: String,
        code: String,
        applicationContext: Context
    ) {
        var url = "${Urls().SERVER}api.php?entry=app&c=user&a=register"
        OkGo.post<String>(url)
            .tag(this)
            .isSpliceUrl(true)
            .params("phone",phone)
            .params("password",password)
            .params("code",code)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var Dbase = DbControl(applicationContext, UserTables.NAME)
                    var gson = Gson()
                    var responseData = gson.fromJson(response?.body(),ResponseModel::class.java)
                    if(responseData.status == 1){
                        var redata = responseData.data
                        Dbase.insertInfo(redata.id,redata.phone)
                        var userInfo = Dbase.selectInfo(redata.id)
                        Log.v("userInfo",userInfo)
                        if(userInfo != null){
                            View.registerSuccess()
                        }else{
                            View.regsiterFail("注册失败")
                        }
                    }else{
                        View.regsiterFail(responseData.message)
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                }
            })
    }

}