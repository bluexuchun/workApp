package widiazine.bluexuchun.workapp.ui.activity

import android.graphics.Color
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_cidentify.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.CidentifyContract
import widiazine.bluexuchun.workapp.presenter.CidentifyPresenter


class CidentifyActivity:BaseActivity(),CidentifyContract.View{

    val presenter = CidentifyPresenter(this)

    val handler by lazy{
        Handler()
    }

    override fun chooseSuccess(type:String,message:String,id:Int) {
        toast(message)
        handler.postDelayed({
            if(type == "child"){
                startActivity<ClevelActivity>("uid" to id)
            }else{
                startActivity<PinfoActivity>("uid" to id)
            }
            finish()
        },2000)

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_cidentify
    }

    override fun init() {
        super.init()
        setFragmentBar(Color.WHITE,Color.BLACK,"")
        setStatusBar(window,1,Color.WHITE)
        header_black_back.visibility = View.VISIBLE
        val bundle = intent.extras
        val uid = bundle.getInt("uid")
        ichild.setOnClickListener {
            presenter.choose(uid,"1",applicationContext)
        }
        iparent.setOnClickListener {
            startActivity<PinfoActivity>()
        }
    }


    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float  = 667F

}