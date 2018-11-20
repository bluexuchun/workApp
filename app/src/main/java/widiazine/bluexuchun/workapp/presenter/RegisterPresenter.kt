package widiazine.bluexuchun.workapp.presenter

import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import org.json.JSONObject
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.RegisterContract
import widiazine.bluexuchun.workapp.utils.Urls

class RegisterPresenter(val View:RegisterContract.View):RegisterContract.Presenter{
    override fun sendCode(phone: String) {
        var params = HashMap<String, String>()
        var url = "${Urls().SERVER}api.php?entry=app&c=user&a=sendSms"
        params.put("phone",phone)
        var jsonObject = JSONObject(params)
        OkGo.post<String>(url)
            .tag(this)
            .upJson(jsonObject)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    Log.v("response","${response}")
                }
            })
    }

    override fun register(phone: String, password: String, rePassword: String, code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}