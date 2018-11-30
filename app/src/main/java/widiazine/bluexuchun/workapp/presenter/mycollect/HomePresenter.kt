package widiazine.bluexuchun.workapp.presenter.mycollect

import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import widiazine.bluexuchun.workapp.contract.mycollect.HomeContract
import widiazine.bluexuchun.workapp.model.collectModel.HomeWorkModel

class HomePresenter(val view:HomeContract.View):HomeContract.Presenter{

    val homeListItems = mutableListOf<HomeWorkModel>()

    override fun loadhw(type:String) {

        homeListItems.clear()

        doAsync {
            val testAarry = mutableListOf<HomeWorkModel>(
                HomeWorkModel(null, true, 1, "2018/11/29"),
                HomeWorkModel(null, false, 2, "2018/11/29"),
                HomeWorkModel(null, true, 3, "2018/11/29"),
                HomeWorkModel(null, true, 4, "2018/11/29")
            )
            for (i in testAarry){
                homeListItems.add(i)
            }
            uiThread {
                view.onLoadSuccess(type)
            }
        }
    }



}