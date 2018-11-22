package widiazine.bluexuchun.workapp.factory

import android.support.v4.app.Fragment
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.fragment.HomeFragment

class FragmentFactory private constructor(){
    val home by lazy{
        HomeFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId:Int):Fragment?{
        when(tabId){
            R.id.fragment_home -> return home
        }
        return null
    }
}