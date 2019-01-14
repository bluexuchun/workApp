package widiazine.bluexuchun.workapp.model.db

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.*
import widiazine.bluexuchun.workapp.model.UserModel

class DbControl(ctx: Context,var table:String){
    var database:DataBaseHelper
    init {
        database = DataBaseHelper(ctx)
    }

    /**
     * 查找所有用户
     */
    fun selectAllInfo():List<UserModel>?{
        var value:List<UserModel>? = null
        database.use {
            var result = select(table).exec {
                parseList(classParser<UserModel>())
            }
            value = result
        }
        return value
    }

    /**
     * 查找指定手机的用户
     */
    fun selectInfo(key:Int):String?{
        var value:String? = null
        var parser = classParser<UserModel>()
        database.use{
            var result = select(table).whereArgs("${UserTables.UID} = \"${key}\"").parseList(parser)
            value = result.toString()
        }
        return value
    }

    /**
     * 插入数据
     */
    fun insertInfo(
                   id:Int?,
                   uid:Int,
                   phone:String,
                   password:String,
                   gid:String?,
                   role_type:String?,
                   realname:String?,
                   avatar:String?,
                   city:String?,
                   address:String?,
                   gender:String?,
                   membershp_id:Int?,
                   createtime:String?,
                   status:Int?,
                   signature:Int?,
                   integral:Int?,
                   start_time:String?,
                   end_time:String?
        ){
        database.use{
            insert(
                table,
                UserTables.ID to id,
                UserTables.UID to uid,
                UserTables.PHONE to phone,
                UserTables.PASSWORD to password,
                UserTables.GID to gid,
                UserTables.ROLE_TYPE to role_type,
                UserTables.REALNAME to realname,
                UserTables.AVATAR to avatar,
                UserTables.CITY to city,
                UserTables.ADDRESS to address,
                UserTables.GENDER to gender,
                UserTables.MEMBERSHP_ID to membershp_id,
                UserTables.CREATETIME to createtime,
                UserTables.STATUS to status,
                UserTables.SIGNATURE to signature,
                UserTables.INTEGRAL to integral,
                UserTables.START_TIME to start_time,
                UserTables.END_TIME to end_time
            )
        }
    }

    /**
     * 更新用户
     */
    fun updateInfo(uid:Int,vararg values: Pair<String, Any?>){
        for(i in values) {
            database.use {
                update(table,
                    i
                ).whereSimple("${UserTables.UID} = \"${uid}\"").exec()
            }
        }
    }

    /**
     * 删除用户
     */
    fun deleteInfo(uid:String){
        database.use {
            delete(table,"${UserTables.UID} = \"${uid}\"")
        }
    }
}