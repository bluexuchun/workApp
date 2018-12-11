package widiazine.bluexuchun.workapp.ui.activity.Goverment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_gover.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.GovermentAdapter
import widiazine.bluexuchun.workapp.contract.goverment.GoverContract
import widiazine.bluexuchun.workapp.presenter.mygover.GoverPresenter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class GoverAcitivity:BaseActivity(),GoverContract.View{

    val presenter = GoverPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.activity_gover
    }

    override fun init() {
        super.init()
        setFragmentBar(Color.WHITE,Color.BLACK,"政策解读")
        setStatusBar(window,1,Color.WHITE)
        header_black_back.visibility = View.VISIBLE

        goverrefresh.apply {
            setOnRefreshListener {
                presenter.loadGover("refresh")
            }
        }

        goverrecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = GovermentAdapter(context,presenter.goverListItem)
        }

        presenter.loadGover("init")

        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }
    }

    override fun loadSuccess(type:String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        goverrefresh.isRefreshing = false
        goverrecycle.adapter?.notifyDataSetChanged()

    }

    override fun loadFail() {
        toast(R.string.load_fail)
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}
