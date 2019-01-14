package widiazine.bluexuchun.workapp.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import org.jetbrains.anko.db.update
import widiazine.bluexuchun.workapp.contract.LoginContract
import widiazine.bluexuchun.workapp.model.UserModel
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.utils.Urls



data class ResponseLoginModel(val data:UserModel,val message:String,val status:Int)

class LoginPresenter(var view: LoginContract.View):LoginContract.Presenter{

    override fun login(
        username: String,
        password: String,
        applicationContext: Context
    ) {
        var url = "${Urls().SERVER}api.php?entry=app&c=user&a=login&do=pass"
        OkGo.post<String>(url)
            .tag(this)
            .isSpliceUrl(true)
            .params("phone",username)
            .params("password",password)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var Dbase = DbControl(applicationContext,UserTables.NAME)
                    var gson = Gson()
                    var responseData = gson.fromJson(response?.body(), ResponseLoginModel::class.java)
                    if(responseData.status == 1){
                        var redata = responseData.data
                        Log.v("redata","${redata}")
                        // 登陆成功 插入数据库
                        Dbase.insertInfo(null,
                            redata.id!!,redata.phone,redata.password,redata.gid,redata.role_type,redata.realname,redata.avatar,redata.city,redata.address,redata.gender,redata.membershp_id,redata.createtime,redata.status,redata.signature,redata.integral,redata.start_time,redata.end_time)
                        var userInfo = Dbase.selectInfo(redata.id!!)
                        if(redata.role_type != null){
                            view.onLoadSuccess(redata.role_type!!,redata.id)
                        }else{
                            view.onLoadSuccess("-1",redata.id)
                        }

//                        Dbase.updateInfo(redata.id!!,"realname" to "xc","phone" to "18521093378")
//                        var userInfo = Dbase.selectInfo(redata.id!!)
//                        Log.v("userInfo","${userInfo}")
                    }else{
                        view.onLoadFail(responseData.message)
                    }
                }
            })
    }
}