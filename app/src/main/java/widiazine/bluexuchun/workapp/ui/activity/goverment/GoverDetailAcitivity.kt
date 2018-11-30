package widiazine.bluexuchun.workapp.ui.activity.goverment

import android.graphics.Color
import android.view.View
import kotlinx.android.synthetic.main.activity_goverdetail.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.goverment.GoverDetailContract
import widiazine.bluexuchun.workapp.presenter.mygover.GoverDetailPresenter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity

class GoverDetailAcitivity:BaseActivity(),GoverDetailContract.view{

    val presenter = GoverDetailPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.activity_goverdetail
    }

    override fun init() {
        super.init()
        setFragmentBar(Color.WHITE,Color.BLACK,"政策解读")
        setStatusBar(window,1,Color.WHITE)
        header_black_back.visibility = View.VISIBLE
        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }
        presenter.loadArticle()
    }

    override fun loadSuccess() {
        var data = presenter.goverdetail
        gdtitle.text = data.govertitle
        gdcontent.text = data.govercontent
        gdtime.text = data.govertime
        gdnums.text = "阅读量${data.governums}"
    }

    override fun loadFail() {
        toast(R.string.load_fail)
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

}
