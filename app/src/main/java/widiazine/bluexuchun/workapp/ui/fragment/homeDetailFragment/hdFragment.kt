package widiazine.bluexuchun.workapp.ui.fragment.homeDetailFragment

import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.fragment_hdfragment.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.fragment.BaseFragment

class hdFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_hdfragment
    }

    override fun fragmentInit() {
        super.fragmentInit()

        v2w_btn.setOnLongClickListener(object :View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                voice_box.visibility = View.VISIBLE
                return true
            }
        })



        hdbox.setOnTouchListener { v, event ->
            Log.v("isInBox","${isTouchView(event,voice_box)}")
            if(isTouchView(event,voice_box)){
                if(voice_box.visibility == View.VISIBLE){
                    voice_box.visibility = View.GONE
                }
            }
            true
        }

        toword_btn.setOnClickListener {
            hwword.visibility = View.VISIBLE
            voice_box.visibility = View.GONE
        }

        backPrev.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    /**
     * 判断是否点击位置是在空间内
     */
    fun isTouchView(event: MotionEvent,view:View): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> println("起始位置：(${event.getX()}),(${event.getY()})")
        }
        var result = getViewPostition(view)
        if(event.getX() < result[0] || event.getX() > result[1] || event.getY() < result[2] || event.getY() > result[3]){
            return true
        }else{
            return false
        }

    }

    /**
     * 获取控件位置
     */
    fun getViewPostition(view: View): IntArray {
        var position = intArrayOf(1,2)
        view.getLocationOnScreen(position)
        var viewLocation = intArrayOf(view.left,view.right,view.top,view.bottom)
        return viewLocation
    }

}