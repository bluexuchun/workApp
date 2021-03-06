package widiazine.bluexuchun.workapp.ui.activity

import android.annotation.TargetApi
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.header.*
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.internal.CustomAdapt
import me.jessyan.autosize.unit.Subunits
import org.jetbrains.anko.act

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
        /**
         * 判断版本
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.v("VERSION","高版本")
            var channelId = "chat"
            var channelName = "聊天消息"
            var importance = NotificationManager.IMPORTANCE_HIGH
            createNotificationChannel(channelId,channelName,importance)
        }else{
            Log.v("VERSION","低版本")
        }
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
            MotionEvent.ACTION_DOWN -> {

            }
        }
        for (item in list){
            var location = getViewPostition(item)
            if(event.getX() > location[0] && event.getX() < location[0] && event.getY() > location[2] && event.getY() < location[3]){

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
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
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
        mWindow.statusBarColor = color
        if(type == 1){
            mWindow.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }else{
            mWindow.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    /**
     * 图片沉浸式状态栏
     */
    fun setImgsBar(){
        statusImgView()
        var statusHeight = getStatusHeight()
        var toolBarParams = toolBar.layoutParams
        toolBarParams.height = statusHeight
    }

    /**
     * fragment 状态栏设置
     * 参数一 头部背景颜色
     * 参数二 字体颜色
     * 参数三 文字
     */
    fun setFragmentBar(backgroundColor:Int?,textColor:Int?,title:String?){
        if(backgroundColor != null){
            commonHeader.setBackgroundColor(backgroundColor!!)
        }
        if(textColor != null){
            header_title.setTextColor(textColor)
        }

        header_title.text = title
        var toolBarParams = toolBar.layoutParams
        toolBarParams.height = 0
    }

    /**
     * 消息队列通知
     */
    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId:String,channelName:String,channelImportance:Int){
        val channel = NotificationChannel(channelId,channelName,channelImportance)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}