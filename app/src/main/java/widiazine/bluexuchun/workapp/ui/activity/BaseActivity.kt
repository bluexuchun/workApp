package widiazine.bluexuchun.workapp.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.internal.CustomAdapt
import me.jessyan.autosize.unit.Subunits

abstract class BaseActivity:AppCompatActivity(),CustomAdapt{

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
        if(specialSit()){
            setAllScreen()
        }
        setContentView(getLayoutResId())
        init()
        configUnits()
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
     * 特殊状况
     */
    abstract fun specialSit():Boolean
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

    /**
     * 获取状态栏的高度
     */
    fun getStatusHeight():Int{
        var result = 0
        var resourceId = resources.getIdentifier("status_bar_height","dimen","android")
        if(resourceId > 0){
            result = resources.getDimensionPixelOffset(resourceId)
        }
        return result
    }

    /**
     * 图片沉浸式状态栏
     */
    fun statusImgView(){
        var decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * 自适应
     */
    fun configUnits(){
        var autoSize = AutoSizeConfig.getInstance().unitsManager
        autoSize.setSupportDP(false)
            .setSupportSubunits(Subunits.MM)
    }

    /**
     * 设置全屏
     */
    fun setAllScreen(){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * 设置状态栏颜色以及文字的颜色模式
     * 文字的颜色分为两种亮色模式(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) 和 暗色模式(SYSTEM_UI_FLAG_VISIBLE)
     * 传入3个参数 当前window 和 type 1 || 2 和 颜色值
     */
    fun setStatusBar(window: Window,type:Int,color:Int){
        var mWindow = window
        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        mWindow.statusBarColor = ContextCompat.getColor(this, color)
        if(type == 1){
            mWindow.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }else{
            mWindow.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }

    }
}