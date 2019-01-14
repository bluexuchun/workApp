package widiazine.bluexuchun.workapp.ui.fragment

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.webView
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.TagsAdapter
import widiazine.bluexuchun.workapp.model.TagsModel

class MainFragment:BaseFragment(){

    val list = mutableListOf<TagsModel>()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun fragmentInit() {


        home_webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        home_webview.settings.javaScriptEnabled = true
//        home_webview.settings.javaScriptCanOpenWindowsAutomatically = true
//        home_webview.settings.loadsImagesAutomatically = true
//        home_webview.settings.defaultTextEncodingName = "utf-8"

        home_webview.loadUrl("https://wq.widiazine.cn/app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile")
        home_webview.webChromeClient = object :WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                Log.v("title","${title}")
                var baseTitle = "作业"
                if(baseTitle != title){
                    homeHeader.visibility = View.GONE
                }else{
                    homeHeader.visibility = View.VISIBLE
                }
                super.onReceivedTitle(view, title)
            }
        }
        home_webview.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        var toggleIndex = "default"

        toggle_btn.setOnClickListener {
            if(toggleIndex == "default"){
                webFrame.visibility = View.GONE
                tagFrame.visibility = View.VISIBLE
                list.clear()
                for (i in 0..50) {
                    var users = TagsModel("施${i}号","#ffffff")
                    list.add(users)
                }
                Log.v("list","${list}")
                var tagList = TagsAdapter(list)
                tags.setAdapter(tagList)
                toggleIndex = "tag"
                homeHeader.setBackgroundColor(resources.getColor(R.color.colorIndex))
                headertitle.setTextColor(resources.getColor(R.color.colorWhite))
            }else{
                tagFrame.visibility = View.GONE
                webFrame.visibility = View.VISIBLE
                toggleIndex = "default"
                homeHeader.setBackgroundColor(resources.getColor(R.color.colorWhite))
                headertitle.setTextColor(resources.getColor(R.color.colorBlack))
            }
        }


    }

}