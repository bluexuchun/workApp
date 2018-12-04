package widiazine.bluexuchun.workapp.ui.fragment.workFragment

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_improvework.*
import org.jetbrains.anko.support.v4.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.work.ImproveAdapter
import widiazine.bluexuchun.workapp.contract.mywork.ImproveContract
import widiazine.bluexuchun.workapp.presenter.mywork.ImproveworkPresenter
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class ImproveworkFragment:BaseFragment(),ImproveContract.View{

    val presenter = ImproveworkPresenter(this)

    override fun loadSuccess(type: String) {
        if(type == "refresh"){
            toast(R.string.refresh_success)
        }
        improveRefresh.isRefreshing = false
        improveRecycle.adapter?.notifyDataSetChanged()
    }

    override fun loadFail() {
        toast(R.string.refresh_fail)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_improvework
    }

    override fun fragmentInit() {
        super.fragmentInit()

        improveRefresh.apply {
            setOnRefreshListener {
                presenter.loadImprovework("refresh")
            }
        }

        improveRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ImproveAdapter(context,presenter.improveLists)
        }

        presenter.loadImprovework("init")

    }
}
