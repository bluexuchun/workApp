package widiazine.bluexuchun.workapp.presenter.mywork

import widiazine.bluexuchun.workapp.contract.mywork.ImproveContract
import widiazine.bluexuchun.workapp.model.workModel.ImproveModel

class ImproveworkPresenter(val view:ImproveContract.View):ImproveContract.Presenter{

    val improveLists = mutableListOf<ImproveModel>()

    override fun loadImprovework(type: String) {
        improveLists.clear()

        val testAry = mutableListOf<ImproveModel>(
            ImproveModel(null,"完形填空","2018-12-1"),
            ImproveModel(null,"选词、填空","2018-12-2"),
            ImproveModel(null,"完形填空","2018-12-3"),
            ImproveModel(null,"选词、填空","2018-12-4")
        )

        for (i in testAry){
            improveLists.add(i)
        }

        view.loadSuccess(type)

    }

}