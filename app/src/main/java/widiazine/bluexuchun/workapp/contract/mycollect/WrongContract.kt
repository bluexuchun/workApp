package widiazine.bluexuchun.workapp.contract.mycollect

interface WrongContract{
    interface Presenter{
        fun loadWrong(type:String)
    }
    interface View{
        fun loadSuccess(type: String)
        fun loadFail()
    }
}