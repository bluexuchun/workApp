package widiazine.bluexuchun.workapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*

abstract class BaseFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(),null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentInit()
    }

    open fun fragmentInit(){

    }

    abstract fun getLayoutResId():Int



}