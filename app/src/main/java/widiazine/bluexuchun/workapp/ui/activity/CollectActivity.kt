package widiazine.bluexuchun.workapp.ui.activity

import android.graphics.Color
import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.CollectAdapter
import widiazine.bluexuchun.workapp.ui.fragment.collectFragment.GovermentFragment
import widiazine.bluexuchun.workapp.ui.fragment.collectFragment.HomeFragment
import widiazine.bluexuchun.workapp.ui.fragment.collectFragment.WrongFragment

class CollectActivity:BaseActivity(),TabLayout.OnTabSelectedListener{
    private val titles = mutableListOf<String>("作业","政策库","错题集")

    override fun getLayoutResId(): Int {
        return R.layout.activity_collect
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667.toFloat()

    override fun init() {
        super.init()
        header_black_back.visibility = View.VISIBLE
        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE,Color.BLACK,"我的收藏")
        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }
        initTabLayout()
        initTabViewPager()
    }

    /**
     * 初始化头部的文本标签
     */
    private fun initTabLayout(){
        for (title in titles){
            tab_layout.addTab(tab_layout.newTab().setText(title))
        }
        tab_layout.addOnTabSelectedListener(this)
        tab_layout.setTabTextColors(resources.getColor(R.color.colorBlack),resources.getColor(R.color.colorMain))
        wrapTabIndicatorToTitle(tab_layout,120,120)
    }

    /**
     * 初始化页面主体的翻页视图
     */
    private fun initTabViewPager(){
        val fragmentList = listOf<Fragment>(HomeFragment(),GovermentFragment(),WrongFragment())
        viewpager.adapter = CollectAdapter(supportFragmentManager, fragmentList)
        /**
         * 监听翻页事件
         */
        viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                //翻页操作停止后，同步切换到对应的文本标签
                tab_layout.getTabAt(position)!!.select()
            }
        })
    }



    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    // 文本标签选中后，同步切换到对应的翻页页面
    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewpager.currentItem = tab!!.position
    }

    /**
     * 设置 tablayout 指标器的宽度
     */
    fun wrapTabIndicatorToTitle(tabLayout: TabLayout,externalMargin: Int,internalMargin:Int){
        var tabStrip = tabLayout.getChildAt(0)
        if(tabStrip is ViewGroup){
            var tabStripGroup = tabStrip as ViewGroup
            var childCount = tabStripGroup.childCount
            for (i in 0..childCount-1){
                var tabView = tabStripGroup.getChildAt(i)
                tabView.minimumWidth = 0
                tabView.setPadding(0,tabView.paddingTop,0,tabView.paddingBottom)
                if(tabView.layoutParams is ViewGroup.MarginLayoutParams){
                    var layoutParams = tabView.layoutParams as ViewGroup.MarginLayoutParams
                    if(i == 0){
                        setMargin(layoutParams,externalMargin,internalMargin)
                    }else if(i == childCount - 1){
                        setMargin(layoutParams,internalMargin,externalMargin)
                    }else{
                        setMargin(layoutParams,internalMargin,internalMargin)
                    }
                }
            }
            tabLayout.requestLayout()
        }
    }
    private fun setMargin(layoutParams: ViewGroup.MarginLayoutParams,start:Int,end:Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            layoutParams.marginStart = start
            layoutParams.marginEnd = end
            layoutParams.leftMargin = start
            layoutParams.rightMargin = end
        }else{
            layoutParams.leftMargin = start
            layoutParams.rightMargin = end
        }
    }
}
