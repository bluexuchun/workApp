package widiazine.bluexuchun.workapp.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * 数据库类
 */

class DataBaseHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "MyDB", null, 1) {

    companion object {
        private var instance: DataBaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DataBaseHelper {
            if (instance == null) {
                instance = DataBaseHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /**
         * 创建数据库
         */
        db?.createTable(
            UserTables.NAME,true,
                UserTables.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                UserTables.UID to INTEGER,
                UserTables.PHONE to TEXT,
                UserTables.PASSWORD to TEXT,
                UserTables.ROLE_TYPE to TEXT,
                UserTables.REALNAME to TEXT,
                UserTables.AVATAR to TEXT,
                UserTables.CITY to TEXT,
                UserTables.ADDRESS to TEXT,
                UserTables.GENDER to TEXT,
                UserTables.MEMBERSHP_ID to INTEGER,
                UserTables.CREATETIME to TEXT,
                UserTables.STATUS to INTEGER,
                UserTables.SIGNATURE to INTEGER,
                UserTables.INTEGRAL to INTEGER,
                UserTables.START_TIME to TEXT,
                UserTables.END_TIME to TEXT
            )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(UserTables.NAME,true)
        onCreate(db)
    }

}
val Context.database:DataBaseHelper
    get() = DataBaseHelper.getInstance(applicationContext)