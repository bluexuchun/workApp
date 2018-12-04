package widiazine.bluexuchun.workapp.ui.fragment.workFragment

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_workhomework.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.collect.HomeAdapter
import widiazine.bluexuchun.workapp.contract.mywork.HomeworkContract
import widiazine.bluexuchun.workapp.presenter.mywork.HomeworkPresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class HomeworkFragment:BaseFragment(),HomeworkContract.View{

    val presenter = HomeworkPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.fragment_workhomework
    }

    override fun fragmentInit() {
        super.fragmentInit()

        hwrefresh.apply {
            setOnRefreshListener {
                presenter.loadHw("refresh")
            }
        }

        hwrecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(context,presenter.homeworkLists)
        }

        presenter.loadHw("init")
    }

    override fun loadFail() {
        toast(R.string.refresh_fail)
    }

    override fun loadSuccess(type: String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        hwrefresh.isRefreshing = false
        hwrecycle.adapter?.notifyDataSetChanged()
    }

}