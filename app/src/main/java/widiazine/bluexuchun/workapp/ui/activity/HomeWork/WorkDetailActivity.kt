package widiazine.bluexuchun.workapp.ui.activity.HomeWork

import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_workdetail.*
import kotlinx.android.synthetic.main.component_tab.view.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.CollectAdapter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity
import widiazine.bluexuchun.workapp.ui.fragment.homeDetailFragment.hdFragment
import widiazine.bluexuchun.workapp.ui.fragment.homeDetailFragment.hdimproveFragment

class WorkDetailActivity:BaseActivity(),TabLayout.OnTabSelectedListener{
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        var tabview = tab?.customView
        tabview?.tabicon!!.visibility = View.GONE
        tabview?.tabtitle.setTextSize(14F)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        wd_viewpager.currentItem = tab!!.position
        var tabview = tab?.customView
        tabview?.tabicon!!.visibility = View.VISIBLE
        tabview?.tabtitle.setTextSize(15F)
    }

    val tabs = listOf<String>("作业","提高")

    override fun getLayoutResId(): Int {
        return R.layout.activity_workdetail
    }

    override fun init() {
        super.init()
        initTablayout()
        initTabViewPager()
        setStatusBar(window, 1,Color.WHITE)
    }

    /**
     * 初始化tablayout
     */
    fun initTablayout(){
        for (i in tabs.indices){
            var tabItem = wd_tablayout.newTab()
            tabItem.setCustomView(getTabView(i))
            if(i == 0){
                var currentitem = tabItem.customView
                currentitem!!.tabicon.visibility = View.VISIBLE
                currentitem!!.tabtitle.setTextSize(15F)
                wd_tablayout.addTab(tabItem,true)
            }else{
                wd_tablayout.addTab(tabItem)
            }
        }
        wd_tablayout.addOnTabSelectedListener(this)
        wd_tablayout.setSelectedTabIndicatorHeight(0)
    }

    /**
     * 自定tablayout
     */
    private fun getTabView(index:Int):View{
        val view = LayoutInflater.from(this).inflate(R.layout.component_tab,null)
        view.tabtitle.setText(tabs[index])
        return view
    }

    /**
     * 初始化fragment
     */
    private fun initTabViewPager(){
        val fragment = listOf<Fragment>(hdFragment(),hdimproveFragment())
        wd_viewpager.adapter = CollectAdapter(supportFragmentManager,fragment)
        wd_viewpager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                wd_tablayout.getTabAt(position)!!.select()
            }
        })
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}