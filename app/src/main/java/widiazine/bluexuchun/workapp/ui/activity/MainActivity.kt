package widiazine.bluexuchun.workapp.ui.activity


import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.moxun.tagcloudlib.view.TagCloudView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.factory.FragmentFactory
import widiazine.bluexuchun.workapp.ui.fragment.LearnFragment
import widiazine.bluexuchun.workapp.ui.fragment.MainFragment
import widiazine.bluexuchun.workapp.ui.fragment.UserFragment
import widiazine.bluexuchun.workapp.ui.fragment.WorkFragment

class MainActivity : BaseActivity(), TagCloudView.OnTagClickListener{
    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667.toFloat()

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        /**
         * 底部标签点击
         */
        bottomTab.setOnTabSelectListener { tabId ->
            var fragment = Fragment()
            when(tabId){
                R.id.home -> {
                    setStatusBar(window,1,Color.WHITE)
                    setFragmentBar(Color.WHITE,Color.BLACK,"首页")
                    commonHeader.visibility = View.VISIBLE
                    fragment = MainFragment()
                }
                R.id.work -> {
                    setStatusBar(window,1,Color.WHITE)
                    commonHeader.visibility = View.GONE
                    fragment = WorkFragment()
                }
                R.id.learn -> {
                    setStatusBar(window,1,Color.BLUE)
                    setFragmentBar(Color.WHITE,Color.BLUE,null)
                    commonHeader.visibility = View.VISIBLE
                    fragment = LearnFragment()
                }
                R.id.user -> {
                    setStatusBar(window,1,Color.WHITE)
                    setFragmentBar(Color.WHITE,Color.BLACK,"个人中心")
                    commonHeader.visibility = View.VISIBLE
                    fragment = UserFragment()
                }
            }
            // 获取fragment管理器
            val beginTransaction = supportFragmentManager.beginTransaction()
            // 将点击的fragment页面 显示到当前屏幕
            beginTransaction.replace(R.id.fragmentframe, fragment)
            // 提交
            beginTransaction.commit()
        }


    }

    /**
     * 点击标签的回调方法
     */
    override fun onItemClick(parent: ViewGroup?, view: View?, position: Int) {

    }





}
