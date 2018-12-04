package widiazine.bluexuchun.workapp.ui.fragment.workFragment

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_wrongwork.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.HomeAdapter
import widiazine.bluexuchun.workapp.contract.mywork.WrongContract
import widiazine.bluexuchun.workapp.presenter.mywork.WrongworkPresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class WrongworkFragment:BaseFragment(),WrongContract.View{

    val presenter = WrongworkPresenter(this)

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

    override fun getLayoutResId(): Int {
        return R.layout.fragment_wrongwork
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
            adapter = HomeAdapter(context,presenter.wrongLists)
        }

        presenter.loadWrong("init")
    }

}