package widiazine.bluexuchun.workapp.contract.mywork

interface ImproveContract{
    interface Presenter{
        fun loadImprovework(type:String)
    }
    interface View{
        fun loadSuccess(type:String)
        fun loadFail()
    }
}