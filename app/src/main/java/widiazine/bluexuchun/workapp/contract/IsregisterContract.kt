package widiazine.bluexuchun.workapp.contract

interface IsregisterContract{
    interface presenter{
        fun searhInfo(key:String)
    }
    interface view{
        fun searchSuccess()
        fun searchFail()
    }
}