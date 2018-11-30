package widiazine.bluexuchun.workapp.contract.mycollect

interface HomeContract{
    interface Presenter{
        fun loadhw(type:String)
    }
    interface View{
        fun onLoadSuccess(type:String)
        fun onLoadFail()
    }
}