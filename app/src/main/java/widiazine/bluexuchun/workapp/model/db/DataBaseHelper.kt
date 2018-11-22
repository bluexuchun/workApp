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
                UserTables.UID to TEXT,
                UserTables.PHONE to TEXT
            )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(UserTables.NAME,true)
        onCreate(db)
    }

}
val Context.database:DataBaseHelper
    get() = DataBaseHelper.getInstance(applicationContext)