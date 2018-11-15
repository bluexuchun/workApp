package widiazine.bluexuchun.workapp.contract

interface LoginContract{
    interface Presenter:BasePresenter{
        /**
         * 登陆方法
         */
        fun login(username:String,password:String)
    }
    interface View{
        /**
         * 登陆成功
         */
        fun onLoadSuccess()
        /**
         * 登陆失败
         */
        fun onLoadFail()
    }
}