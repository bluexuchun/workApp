package widiazine.bluexuchun.workapp.model.db

import android.content.Context
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
    fun selectInfo(key:String):String?{
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
    fun insertInfo(uid:String,phone:String){
        database.use{
            insert(table, UserTables.UID to uid,UserTables.PHONE to phone)
        }
    }

    /**
     * 更新用户
     */
    fun updateInfo(uid:String,phone: String){
        database.use {
            update(table,
                UserTables.PHONE to phone).whereSimple("${UserTables.UID} = \"${uid}\"").exec()
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