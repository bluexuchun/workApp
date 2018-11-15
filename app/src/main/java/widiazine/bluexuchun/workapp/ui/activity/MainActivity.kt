package widiazine.bluexuchun.workapp.ui.activity

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.support.v4.graphics.ColorUtils
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import com.moxun.tagcloudlib.view.TagCloudView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.activityManager
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.TagsAdapter
import widiazine.bluexuchun.workapp.model.TagsModel
import kotlin.random.Random

class MainActivity : BaseActivity(), TagCloudView.OnTagClickListener{

    val list = mutableListOf<TagsModel>()

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        for (i in 0..30) {
            /**
             * 随机颜色
             */
            var r = Integer.toHexString(Random.nextInt(256)).toUpperCase()
            var g = Integer.toHexString(Random.nextInt(256)).toUpperCase()
            var b = Integer.toHexString(Random.nextInt(256)).toUpperCase()

            r = if( r.count() == 1 ) { "0" + r } else { r }
            g = if( g.count() == 1 ) { "0" + g } else { g }
            b = if( b.count() == 1 ) { "0" + b } else { b }

            var userColor = "#" + r + g + b

            var users = TagsModel("施${i}号",userColor)
            list.add(users)
        }
        var tagList = TagsAdapter(list)
        tags.setAdapter(tagList)
    }

    /**
     * 点击标签的回调方法
     */
    override fun onItemClick(parent: ViewGroup?, view: View?, position: Int) {

    }

    /**
     * 图片沉浸式状态栏
     */
    fun setStatusBar(){
        statusImgView()
        var statusHeight = getStatusHeight()
        var toolBarParams = toolBar.layoutParams
        toolBarParams.height = statusHeight
    }

}
