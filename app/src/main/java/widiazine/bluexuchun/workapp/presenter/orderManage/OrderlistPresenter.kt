package widiazine.bluexuchun.workapp.presenter.orderManage

import widiazine.bluexuchun.workapp.contract.order.OrderContract
import widiazine.bluexuchun.workapp.model.OrderModel.OrderlistModel

class OrderlistPresenter(val view: OrderContract.view):OrderContract.Presenter{
    val orderList = mutableListOf<OrderlistModel>()
    override fun loadOrder(type: String) {
        val testAry = mutableListOf<OrderlistModel>(
            OrderlistModel(1,null,"10元","2019-1-14 17:35",false),
            OrderlistModel(2,null,"20元","2019-1-13 17:35",true),
            OrderlistModel(3,null,"30元","2019-1-15 17:35",false)
        )
        for (i in testAry){
            orderList.add(i)
        }

        uiThread {
            view.loadSuccess(type)
        }
    }

}