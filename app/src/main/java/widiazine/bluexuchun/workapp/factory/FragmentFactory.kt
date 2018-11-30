package widiazine.bluexuchun.workapp.factory

import android.support.v4.app.Fragment
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.fragment.MainFragment
import widiazine.bluexuchun.workapp.ui.fragment.LearnFragment
import widiazine.bluexuchun.workapp.ui.fragment.UserFragment
import widiazine.bluexuchun.workapp.ui.fragment.WorkFragment

class FragmentFactory private constructor(){

    /**
     * 创建conversation contact dynamic的fragment的实例id
     */
    val Home by lazy{
        MainFragment()
    }

    val Learn by lazy{
        LearnFragment()
    }

    val Work by lazy {
        WorkFragment()
    }

    val User by lazy {
        UserFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    //返回实例id
    fun getFragment(tabId:Int): Fragment?{
        when(tabId){
            R.id.home -> return Home
            R.id.learn -> return Learn
            R.id.work -> return Work
            R.id.user -> return User
        }
        return null
    }
}