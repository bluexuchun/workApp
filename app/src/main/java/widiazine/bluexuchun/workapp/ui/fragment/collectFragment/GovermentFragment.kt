package widiazine.bluexuchun.workapp.ui.fragment.collectFragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.fragment_collectgoverment.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.GovermentAdapter
import widiazine.bluexuchun.workapp.contract.mycollect.GovermentContract
import widiazine.bluexuchun.workapp.presenter.mycollect.GovermentPresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class GovermentFragment:BaseFragment(),GovermentContract.View{

    val presenter = GovermentPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.fragment_collectgoverment
    }

    override fun fragmentInit() {
        super.fragmentInit()

        goverfresh.apply {

            /**
             * 监听手动刷新
             */
            setOnRefreshListener {
                presenter.loadGover("refresh")
            }
        }

        govercycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = GovermentAdapter(context,presenter.goverListItems)
        }

        presenter.loadGover("init")

    }

    override fun loadSuccess(type: String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        goverfresh.isRefreshing = false
        govercycler.adapter?.notifyDataSetChanged()
    }

    override fun loadFail(type: String) {
        toast(R.string.refresh_fail)
    }
}