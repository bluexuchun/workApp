package widiazine.bluexuchun.workapp.contract.mywork

interface HomeworkContract{
    interface Presenter{
        fun loadHw(type:String)
    }
    interface View{
        fun loadSuccess(type:String)
        fun loadFail()
    }
}