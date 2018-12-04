package widiazine.bluexuchun.workapp.presenter.mywork

import widiazine.bluexuchun.workapp.contract.mywork.WrongContract
import widiazine.bluexuchun.workapp.model.collectModel.HomeWorkModel

class WrongworkPresenter(val view:WrongContract.View):WrongContract.Presenter{

    val wrongLists = mutableListOf<HomeWorkModel>()

    override fun loadWrong(type: String) {

        wrongLists.clear()

        val test = mutableListOf<HomeWorkModel>(
            HomeWorkModel(null,true,1,"2018-12-1"),
            HomeWorkModel(null,false,2,"2018-12-2"),
            HomeWorkModel(null,true,3,"2018-12-3"),
            HomeWorkModel(null,false,4,"2018-12-4")
        )

        for (i in test){
            wrongLists.add(i)
        }

        view.loadSuccess(type)

    }

}