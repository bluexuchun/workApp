package widiazine.bluexuchun.workapp.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.component_tab.*
import kotlinx.android.synthetic.main.component_tab.view.*
import kotlinx.android.synthetic.main.fragment_work.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.CollectAdapter
import widiazine.bluexuchun.workapp.ui.fragment.workFragment.HomeworkFragment
import widiazine.bluexuchun.workapp.ui.fragment.workFragment.ImproveworkFragment
import widiazine.bluexuchun.workapp.ui.fragment.workFragment.WrongworkFragment

class WorkFragment:BaseFragment(),TabLayout.OnTabSelectedListener{

    val tabs = listOf<String>("作业","错题集","提高库")

    override fun getLayoutResId(): Int {
        return R.layout.fragment_work
    }

    override fun fragmentInit() {
        super.fragmentInit()
        initTabLayout()
        initTabViewPager()
    }

    /**
     * 初始化tab的标签
     */
    private fun initTabLayout(){
        for (i in tabs.indices){
            var tabItem = work_layout.newTab()
            tabItem.setCustomView(getTabView(i))
            if(i == 0){
                var currentitem = tabItem.customView
                currentitem!!.tabicon.visibility = View.VISIBLE
                currentitem!!.tabtitle.setTextSize(15F)
                work_layout.addTab(tabItem,true)
            }else{
                work_layout.addTab(tabItem)
            }
        }
        work_layout.addOnTabSelectedListener(this)
        work_layout.setSelectedTabIndicatorHeight(0)
    }

    /**
     * 自定tablayout
     */
    private fun getTabView(index:Int):View{
        val view = LayoutInflater.from(activity).inflate(R.layout.component_tab,null)
        view.tabtitle.setText(tabs[index])
        return view
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        var tabview = tab?.customView
        tabview?.tabicon!!.visibility = View.GONE
        tabview?.tabtitle!!.setTextSize(14F)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        var tabview = tab?.customView
        tabview?.tabicon!!.visibility = View.VISIBLE
        tabview?.tabtitle!!.setTextSize(15F)
        work_viewpager.currentItem = tab!!.position
    }

    /**
     * 初始化fragment
     */
    private fun initTabViewPager(){
        val fragment = listOf<Fragment>(HomeworkFragment(),WrongworkFragment(),ImproveworkFragment())
        work_viewpager.adapter = CollectAdapter(childFragmentManager,fragment)
        work_viewpager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                work_layout.getTabAt(position)!!.select()
            }
        })


    }

}
