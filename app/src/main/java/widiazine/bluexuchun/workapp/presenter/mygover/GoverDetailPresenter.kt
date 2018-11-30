package widiazine.bluexuchun.workapp.presenter.mygover

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import widiazine.bluexuchun.workapp.contract.goverment.GoverDetailContract
import widiazine.bluexuchun.workapp.model.collectModel.GovermentModel

class GoverDetailPresenter(val view:GoverDetailContract.view):GoverDetailContract.presenter{

    var goverdetail = GovermentModel(null,null,null,null,null)

    override fun loadArticle() {
        doAsync {
            goverdetail = GovermentModel(null,"测试1","测试1","2018年11月30日15:34:03","2.5w")
            uiThread {
                view.loadSuccess()
            }
        }

    }
}