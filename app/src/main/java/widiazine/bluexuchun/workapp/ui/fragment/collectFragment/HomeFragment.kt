package widiazine.bluexuchun.workapp.ui.fragment.collectFragment

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_collecthome.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.HomeAdapter
import widiazine.bluexuchun.workapp.contract.mycollect.HomeContract
import widiazine.bluexuchun.workapp.presenter.mycollect.HomePresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class HomeFragment:BaseFragment(), HomeContract.View{

    val presenter = HomePresenter(this)

    override fun onLoadSuccess(type:String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        homerefresh.isRefreshing = false
        homerecycler.adapter?.notifyDataSetChanged()
    }

    override fun onLoadFail() {
        homerefresh.isRefreshing = false
        toast(R.string.refresh_fail)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_collecthome
    }

    override fun fragmentInit() {
        super.fragmentInit()

        homerefresh.apply{
            setOnRefreshListener {
                presenter.loadhw("refresh")
            }
        }

        /**
         * 渲染recyclerview
         */
        homerecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(context,presenter.homeListItems)
        }

        presenter.loadhw("init")
    }
}
