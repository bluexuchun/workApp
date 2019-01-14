package widiazine.bluexuchun.workapp.ui.activity.OrderManage

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_orderlist.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.OrderlistAdapter
import widiazine.bluexuchun.workapp.contract.order.OrderContract
import widiazine.bluexuchun.workapp.presenter.orderManage.OrderlistPresenter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class OrderListActivity:BaseActivity(),OrderContract.view{

    val presenter = OrderlistPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.activity_orderlist
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        setImgsBar()

        order_refresh.apply {
            setOnRefreshListener {
                presenter.loadOrder("refresh")
            }
        }
        order_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = OrderlistAdapter(context,presenter.orderList)
        }

        presenter.loadOrder("init")

        header_back.visibility = View.VISIBLE
        common_title.text = "我的订单"
        header_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }
    }

    override fun loadSuccess(type: String) {
        if(type == "refresh"){
            toast(R.string.load_success)
        }
        order_refresh.isRefreshing = false
        order_recycler.adapter.notifyDataSetChanged()
    }

    override fun loadFail(message: String) {
        toast(R.string.load_fail)
    }

}