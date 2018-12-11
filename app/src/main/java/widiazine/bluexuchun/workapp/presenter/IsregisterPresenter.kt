package widiazine.bluexuchun.workapp.presenter

import widiazine.bluexuchun.workapp.contract.IsregisterContract
import widiazine.bluexuchun.workapp.model.ChildModel

class IsregisterPresenter(
    var view:IsregisterContract.view
):IsregisterContract.presenter{

    var searchList = mutableListOf<ChildModel>()

    override fun searhInfo(key: String) {
        searchList.clear()
        //做请求数据
        //结果
        var test = mutableListOf<ChildModel>(
            ChildModel(1,null,"孙东波","高一","皮皮虾高中",false),
            ChildModel(2,null,"孙东波1","高一","皮皮虾高中",false),
            ChildModel(3,null,"孙东波2","高一","皮皮虾高中",false)
        )
        for (i in test){
            searchList.add(i)
        }

        view.searchSuccess()
    }

}