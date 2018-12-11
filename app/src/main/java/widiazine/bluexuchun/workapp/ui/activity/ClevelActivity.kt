package widiazine.bluexuchun.workapp.ui.activity

import android.graphics.Color
import android.view.View
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R

class ClevelActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_clevel
    }

    override fun init() {
        super.init()
        setFragmentBar(Color.WHITE,Color.BLACK,"")
        setStatusBar(window,1,Color.WHITE)
        header_black_back.visibility = View.VISIBLE
    }

    override fun specialSit(): Boolean  = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}