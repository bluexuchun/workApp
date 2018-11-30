package widiazine.bluexuchun.workapp.presenter.mygover

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import widiazine.bluexuchun.workapp.contract.goverment.GoverContract
import widiazine.bluexuchun.workapp.model.collectModel.GovermentModel

class GoverPresenter(val view:GoverContract.View):GoverContract.Presenter{
    val goverListItem = mutableListOf<GovermentModel>()

    override fun loadGover(type:String) {
        doAsync {
            val testAry = mutableListOf<GovermentModel>(
                GovermentModel(null,"如何提高英语成绩","我的经验告诉我学好英语要做到四个字：听、说、读、写。听：最好在早上听上半个小时左右的英语听力，坚持每天听，时间不要太长，半个小...","2018-11-30 10:19:50","1.3W"),
                GovermentModel(null,"如何提高语文成绩","我的经验告诉我学好语文要做到四个字：听、说、读、写。听：最好在早上听上半个小时左右的英语听力，坚持每天听，时间不要太长，半个小...","2018-11-29 10:19:50","1.2W"),
                GovermentModel(null,"如何提高数学成绩","我的经验告诉我学好数学要做到四个字：听、说、读、写。听：最好在早上听上半个小时左右的英语听力，坚持每天听，时间不要太长，半个小...","2018-11-28 10:19:50","1.1W"),
                GovermentModel(null,"如何提高政治成绩","我的经验告诉我学好政治要做到四个字：听、说、读、写。听：最好在早上听上半个小时左右的英语听力，坚持每天听，时间不要太长，半个小...","2018-11-27 10:19:50","1.0W"),
                GovermentModel(null,"如何提高历史成绩","我的经验告诉我学好历史要做到四个字：听、说、读、写。听：最好在早上听上半个小时左右的英语听力，坚持每天听，时间不要太长，半个小...","2018-11-26 10:19:50","1.5W")
            )
            for (i in testAry){
                goverListItem.add(i)
            }

            uiThread {
                view.loadSuccess(type)
            }
        }
    }

}