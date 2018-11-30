package widiazine.bluexuchun.workapp.ui.fragment.collectFragment

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_collectwrong.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.WrongAdapter
import widiazine.bluexuchun.workapp.contract.mycollect.WrongContract
import widiazine.bluexuchun.workapp.presenter.mycollect.WrongPresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class WrongFragment:BaseFragment(),WrongContract.View{

    val presenter = WrongPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.fragment_collectwrong
    }

    override fun fragmentInit() {
        super.fragmentInit()

        wrongrefresh.apply {

            setOnRefreshListener {
                presenter.loadWrong("refresh")
            }
        }

        wrongrecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = WrongAdapter(context,presenter.wrongListItems)

        }
        presenter.loadWrong("init")
    }

    override fun loadSuccess(type: String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        wrongrefresh.isRefreshing = false
        wrongrecycler.adapter?.notifyDataSetChanged()
    }

    override fun loadFail() {
        toast(R.string.refresh_fail)
    }

}