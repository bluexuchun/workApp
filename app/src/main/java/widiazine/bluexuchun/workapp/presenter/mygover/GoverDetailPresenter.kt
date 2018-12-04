package widiazine.bluexuchun.workapp.presenter.mygover

import android.content.Context
import android.os.Handler
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import widiazine.bluexuchun.workapp.contract.goverment.GoverDetailContract
import widiazine.bluexuchun.workapp.model.collectModel.GovermentModel

class GoverDetailPresenter(val view:GoverDetailContract.view):GoverDetailContract.presenter{

    private val handler = Handler()
    private var count = 0
    var isCount = true

    inner private class Counter:Runnable{
        override fun run() {
            if(isCount){
                count++
                Log.v("count","${count}")
                handler.postDelayed(this,1000)
                if(count == 10){
                    view.sendMessage("好消息","啊哈哈哈哈")
                    isCount = false
                }
            }else{
                handler.removeCallbacks(Counter())
            }
        }
    }
    var goverdetail = GovermentModel(null,null,null,null,null)

    override fun loadArticle() {
        doAsync {
            goverdetail = GovermentModel(null,"测试1","测试1","2018年11月30日15:34:03","2.5w")
            startHandler()

            uiThread {
                view.loadSuccess()
            }
        }
    }

    open fun startHandler(){
        handler.post(Counter())
    }


}