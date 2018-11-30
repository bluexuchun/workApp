package widiazine.bluexuchun.workapp.contract.mycollect

interface GovermentContract{
    interface Presenter{
        fun loadGover(type:String)
    }
    interface View{
        fun loadSuccess(type:String)
        fun loadFail(type:String)
    }
}