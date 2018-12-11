package widiazine.bluexuchun.workapp.ui.activity.Goverment

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_goverdetail.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.contract.goverment.GoverDetailContract
import widiazine.bluexuchun.workapp.presenter.mygover.GoverDetailPresenter
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity
import widiazine.bluexuchun.workapp.ui.activity.MainActivity

class GoverDetailAcitivity:BaseActivity(),GoverDetailContract.view{
    override fun sendMessage(title: String, message: String) {
        /**
         * 声明一个点击通知消息时触发的动作意图
         */
        val clickIntent = intentFor<MainActivity>()
        val piClick = PendingIntent.getActivity(this,0,clickIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(this,"chat")
                .setContentIntent(piClick)
                .setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_loading)
                .setAutoCancel(true)
                .build()
            manager.notify(1,notification)
        }else{
            val builder = Notification.Builder(this)
            val notify = builder
                .setContentIntent(piClick)
                .setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_loading)
                .setAutoCancel(true)
                .build()
            var notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifyMgr.notify(0x22222,notify)
        }

    }

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
            presenter.isCount = false
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
