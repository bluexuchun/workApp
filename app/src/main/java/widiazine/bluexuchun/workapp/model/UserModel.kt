package widiazine.bluexuchun.workapp.model

data class UserModel(
    val id:Int?,
    val uid:Int,
    val phone:String,
    val password:String,
    val gid:String?,
    val role_type:String?,
    val realname:String?,
    val avatar:String?,
    val city:String?,
    val address:String?,
    val gender:String?,
    val membershp_id:Int?,
    val createtime:String?,
    val status:Int?,
    val signature:Int?,
    val integral:Int?,
    val start_time:String?,
    val end_time:String?
)