package widiazine.bluexuchun.workapp.presenter.mycollect

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import widiazine.bluexuchun.workapp.contract.mycollect.WrongContract
import widiazine.bluexuchun.workapp.model.collectModel.WrongModel

class WrongPresenter(val view:WrongContract.View):WrongContract.Presenter{

    val wrongListItems = mutableListOf<WrongModel>()

    override fun loadWrong(type: String) {
        wrongListItems.clear()

        doAsync {
            val testAarry = mutableListOf<WrongModel>(
                WrongModel(null, true, 1, "2018/11/29"),
                WrongModel(null, false, 2, "2018/11/29"),
                WrongModel(null, true, 3, "2018/11/29"),
                WrongModel(null, true, 4, "2018/11/29")
            )

            for (i in testAarry){
                wrongListItems.add(i)
            }
            uiThread {
                view.loadSuccess(type)
            }
        }
    }

}