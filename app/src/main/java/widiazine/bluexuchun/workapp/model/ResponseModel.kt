package widiazine.bluexuchun.workapp.model

data class responseUser(val phone: String,val id:String)

data class ResponseModel(val data:responseUser, val message:String, val status:Int)

data class ResponseCommon(val data:String, val message:String, val status:Int)