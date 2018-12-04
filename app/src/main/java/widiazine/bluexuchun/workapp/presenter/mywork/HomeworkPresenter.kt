package widiazine.bluexuchun.workapp.presenter.mywork

import widiazine.bluexuchun.workapp.contract.mywork.HomeworkContract
import widiazine.bluexuchun.workapp.model.collectModel.HomeWorkModel

class HomeworkPresenter(val view:HomeworkContract.View):HomeworkContract.Presenter{

    val homeworkLists = mutableListOf<HomeWorkModel>()

    override fun loadHw(type: String) {

        homeworkLists.clear()

        val test = mutableListOf<HomeWorkModel>(
            HomeWorkModel(null,true,1,"2018-12-1"),
            HomeWorkModel(null,false,2,"2018-12-2"),
            HomeWorkModel(null,true,3,"2018-12-3"),
            HomeWorkModel(null,false,4,"2018-12-4")
        )
        for (i in test){
            homeworkLists.add(i)
        }

        view.loadSuccess(type)
    }
}