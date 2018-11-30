package widiazine.bluexuchun.workapp.contract.goverment

interface GoverContract{
    interface Presenter{
        fun loadGover(type:String)
    }
    interface View{
        fun loadSuccess(type:String)
        fun loadFail()
    }
}