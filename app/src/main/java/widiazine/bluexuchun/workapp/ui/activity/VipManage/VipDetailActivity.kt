package widiazine.bluexuchun.workapp.ui.activity.VipManage

import android.app.AlertDialog
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_vipdetail.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.ui.activity.BaseActivity
import widiazine.bluexuchun.workapp.ui.activity.PaySuccessActivity

class VipDetailActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_vipdetail
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        /**
         * 获取vip的id
         */
        var bundle = intent.extras
        var vipid = bundle.getString("vipid")
        Log.v("id","${vipid}")

        /**
         * 设置状态栏
         */
        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE, Color.BLACK,"周卡详情")
        header_black_back.visibility = View.VISIBLE
        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }

        /**
         * 购买弹窗
         */
        buybtn.setOnClickListener {
            var factory = LayoutInflater.from(this@VipDetailActivity)
            var dialogView = factory.inflate(R.layout.component_payalert,null)

            /**
             * 支付的方式
             * 默认是微信 - 1
             * 支付宝 - 2
             * 余额 - 3
             **/
            var paytype = 1

            /**
             * 判断用户的余额是否充足
             */
            var credittitle = dialogView.findViewById<TextView>(R.id.creditrest)
            credittitle.text = "余额充足"

            var submitbtn = dialogView.findViewById<Button>(R.id.submit)

            // 配置控件
            var wechat_btn = dialogView.findViewById<RelativeLayout>(R.id.wechat_btn)
            var alipay_btn = dialogView.findViewById<RelativeLayout>(R.id.alipay_btn)
            var credit_btn = dialogView.findViewById<RelativeLayout>(R.id.credit_btn)

            var wechat_select = dialogView.findViewById<ImageView>(R.id.wechat_select)
            var alipay_select = dialogView.findViewById<ImageView>(R.id.alipay_select)
            var credit_select = dialogView.findViewById<ImageView>(R.id.credit_select)

            alipay_btn.setOnClickListener {
                alipay_select.setImageResource(R.mipmap.choose)
                wechat_select.setImageResource(R.mipmap.nochoose)
                credit_select.setImageResource(R.mipmap.nochoose)
                paytype = 2
            }
            wechat_btn.setOnClickListener {
                alipay_select.setImageResource(R.mipmap.nochoose)
                wechat_select.setImageResource(R.mipmap.choose)
                credit_select.setImageResource(R.mipmap.nochoose)
                paytype = 1
            }
            credit_btn.setOnClickListener {
                alipay_select.setImageResource(R.mipmap.nochoose)
                wechat_select.setImageResource(R.mipmap.nochoose)
                credit_select.setImageResource(R.mipmap.choose)
                paytype = 3
            }

            var dialogBox = AlertDialog.Builder(this@VipDetailActivity)
            dialogBox.setView(dialogView)
            var dialogItem = dialogBox.create()
            dialogItem.show()
            var m = windowManager
            var screenInfo = m.defaultDisplay
            var dl = dialogItem.window.attributes
            dl.width = (screenInfo.width * 0.9).toInt()
            dialogItem.window.attributes = dl

            /**
             * 购买支付调起接口
             */
            submitbtn.setOnClickListener {
                Log.v("paytype","${paytype}")
                toast("支付成功...支付方式为${paytype}")
                startActivity<PaySuccessActivity>()
            }
        }
    }

}