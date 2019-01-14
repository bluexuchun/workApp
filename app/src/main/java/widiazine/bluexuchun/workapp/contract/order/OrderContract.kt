package widiazine.bluexuchun.workapp.contract.order

import widiazine.bluexuchun.workapp.contract.BasePresenter

interface OrderContract{
    interface Presenter:BasePresenter{
        fun loadOrder(type:String)
    }
    interface view{
        fun loadSuccess(type:String)
        fun loadFail(message:String)
    }
}