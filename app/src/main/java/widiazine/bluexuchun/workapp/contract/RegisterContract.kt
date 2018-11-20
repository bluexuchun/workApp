package widiazine.bluexuchun.workapp.contract

interface RegisterContract{
    interface Presenter:BasePresenter{
        // 发送验证码
        fun sendCode(phone:String)
        // 注册
        fun register(phone: String,password:String,rePassword:String,code:String)
    }
    interface View{
        // 发送验证码成功
        fun sendSuccess()
        // 发送验证码失败
        fun sendFail()
        // 发送验证码中
        fun sendIng()
        // 注册中
        fun registerIng()
        // 注册成功
        fun registerSuccess()
        // 注册失败
        fun regsiterFail()
    }
}