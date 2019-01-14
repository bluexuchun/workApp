package widiazine.bluexuchun.workapp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_clevel.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.ClevelContract
import widiazine.bluexuchun.workapp.presenter.ClevelPresenter

class ClevelActivity:BaseActivity(),ClevelContract.View{

    val presenter = ClevelPresenter(this)

    val handler by lazy{
        Handler()
    }

    override fun chooseSuccess(message: String,uid:Int) {
        toast(message)
        handler.postDelayed({
            startActivity<InfoActivity>("uid" to uid)
        },2000)

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_clevel
    }

    override fun init() {
        super.init()
        setFragmentBar(Color.WHITE,Color.BLACK,"")
        setStatusBar(window,1,Color.WHITE)
        header_black_back.visibility = View.VISIBLE
        val bundle = intent.extras
        val id = bundle.getInt("uid")
        Log.v("id","${id}")
        /**
         * 学前
         */
        sbefore.setOnClickListener {
            presenter.chooseLevel("1",id,applicationContext)
        }
        /**
         * 小学
         */
        sprimary.setOnClickListener {
            presenter.chooseLevel("2",id,applicationContext)
        }
        /**
         * 初中
         */
        sjunior.setOnClickListener {
            presenter.chooseLevel("3",id,applicationContext)
        }
        /**
         * 高中
         */
        shigh.setOnClickListener {
            presenter.chooseLevel("4",id,applicationContext)
        }
    }

    override fun specialSit(): Boolean  = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}