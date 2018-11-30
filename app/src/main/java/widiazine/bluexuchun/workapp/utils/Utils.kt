package widiazine.bluexuchun.workapp.utils

import java.text.SimpleDateFormat

class Utils{

    fun isEmpty(string: String):Boolean{
        if(string.length > 0){
            return true
        }else{
            return false
        }
    }

    /**
     * 获取当前完整的日期和时间
     */
    fun getNowDateTime():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(this)
    }

    /**
     * 返回日期
     */
    fun getNowDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(this)
    }

    /**
     * 获取时间
     */
    fun getNowTime():String{
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(this)
    }

    /**
     * 返回开发者指定格式的日期时间
     */
    fun getFormatTime(format: String=""):String{
        var ft = format
        val sdf = if(!ft.isEmpty()) {
            SimpleDateFormat(ft)
        }else{
            SimpleDateFormat("yyyyMMddHHmmss")
        }
        return sdf.format(this)
    }

}