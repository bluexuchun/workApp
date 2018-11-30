package widiazine.bluexuchun.workapp.contract.goverment

interface GoverDetailContract{
    interface presenter{
        fun loadArticle()
    }
    interface view{
        fun loadSuccess()
        fun loadFail()
    }
}