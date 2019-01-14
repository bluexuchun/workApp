package widiazine.bluexuchun.workapp.ui.activity.VipManage

import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_vip.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.CollectAdapter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity
import widiazine.bluexuchun.workapp.ui.fragment.vipFragment.monthFragment
import widiazine.bluexuchun.workapp.ui.fragment.vipFragment.weekFragment

class VipActivity: BaseActivity(),TabLayout.OnTabSelectedListener{

    private val titles = mutableListOf<String>("周卡","月卡")

    override fun getLayoutResId(): Int {
        return R.layout.activity_vip
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE, Color.BLACK,"VIP购买")
        header_black_back.visibility = View.VISIBLE
        common_title.text = "购买明细"
        common_title.visibility = View.VISIBLE

        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }

        common_title.setOnClickListener {
            startActivity<VipListActivity>()
        }

        initTabLayout()
        initTabViewPager()
    }

    /**
     * 初始化头部的文本标签
     */
    private fun initTabLayout(){
        for (title in titles.indices){
            vip_tab.addTab(vip_tab.newTab().setText(titles[title]))
        }
        vip_tab.addOnTabSelectedListener(this)
        vip_tab.setSelectedTabIndicatorHeight(0)
    }

    /**
     * 初始化页面主体的翻页视图
     */
    private fun initTabViewPager(){
        val fragmentList = listOf<Fragment>(weekFragment(),monthFragment())
        vip_vp.adapter = CollectAdapter(supportFragmentManager, fragmentList)
        /**
         * 监听翻页事件
         */
        vip_vp.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                //翻页操作停止后，同步切换到对应的文本标签
                vip_tab.getTabAt(position)!!.select()
            }
        })
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        vip_vp.currentItem = tab!!.position
        Log.v("index","${tab.position}")
        if(tab.position == 1){
            vip_tab.background = resources.getDrawable(R.drawable.btn_tabs)
        }else{
            vip_tab.background = resources.getDrawable(R.drawable.btn_tabs_active)
        }
    }


}