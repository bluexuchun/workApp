package widiazine.bluexuchun.workapp.presenter

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import widiazine.bluexuchun.workapp.contract.RegisterContract
import java.net.URL

class RegisterPresenter(val View:RegisterContract.View):RegisterContract.Presenter{
    override fun sendCode(phone: String) {

    }

    override fun register(phone: String, password: String, rePassword: String, code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}