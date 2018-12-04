package widiazine.bluexuchun.workapp.contract.mywork

interface WrongContract{
    interface Presenter{
        fun loadWrong(type:String)
    }
    interface View{
        fun loadSuccess(type:String)
        fun loadFail()
    }
}