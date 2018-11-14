package widiazine.bluexuchun.workapp.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

abstract class BaseActivity:AppCompatActivity(){

    /**
     * 对话框
     */
    val progressDialog by lazy{
        ProgressDialog(this)
    }

    /**
     * 软键盘
     */
    val inputMethodManager by lazy{
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    /**
     * 初始化
     * 每个activity 都有自己的初始化
     */
    open fun init() {

    }

    /**
     * 返回当前activity的id
     */
    abstract fun getLayoutResId(): Int

    /**
     * 展示对话框 progress
     */
    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    /**
     * 隐藏对话框
     */
    fun hideProgress(){
        progressDialog.dismiss()
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(){
        // currentFocus.windowToken 当前屏幕的token
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }

    /**
     * 判断是否点击位置是在空间内
     */
    fun isTouchView(event: MotionEvent, list: List<View>): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> println("起始位置：(${event.getX()}),(${event.getY()})")
        }
        for (item in list){
            var location = getViewPostition(item)
            if(event.getX() > location[0] && event.getX() < location[0] && event.getY() > location[2] && event.getY() < location[3]){
                println("在控件内，不做操作")
            }else{
                hideKeyboard()
            }
        }
        return true
    }

    /**
     * 获取控件位置
     */
    fun getViewPostition(view: View): IntArray {
        var position = intArrayOf(1,2)
        view.getLocationInWindow(position)
        var viewLocation = intArrayOf(position[0],position[0] + view.width,position[1],position[1] + view.height)
        return viewLocation
    }

    /**
     * 申请权限
     */

    fun applyWriteExternalStoragePermission(array: Array<String>) {
        ActivityCompat.requestPermissions(this,array,0)
    }

    /**
     * 检查是否有权限
     */
    fun hasWriteExternalStoragePermission(permission:String): Boolean {
        var result = ActivityCompat.checkSelfPermission(this, permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

}