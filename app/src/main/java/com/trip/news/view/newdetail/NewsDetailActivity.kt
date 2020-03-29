package com.trip.news.view.newdetail

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.webkit.*
import androidx.annotation.RequiresApi
import com.trip.news.R
import com.trip.news.base.view.BaseActivity
import com.trip.news.view.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : BaseActivity(R.layout.activity_news_detail){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWebView()
    }

    private fun initWebView() {
        val webViewSetting = wv_news.settings
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true

        //브라우저 크롬때문에 설정
        wv_news.webChromeClient = WebChromeClient()

        //웹 뷰에 직접 띄우기 위해 설정
        wv_news.webViewClient = WebViewClientClass()

        //WebView에 네트워크 상태를 알리는 것
        wv_news.setNetworkAvailable(true)

        //선택한 Link로 WebView에 띄운다.
        val link = intent.getStringExtra(NewsListActivity.INTENT_ACTIVITY_NEWS_DETAIL)
        wv_news.loadUrl(link)
    }

    inner class WebViewClientClass : WebViewClient() {

        //페이지 이동
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            Log.i(javaClass.simpleName, "UrlLoading : $url")
            view.loadUrl(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.run {
                loadUrl(request?.url.toString())
            }

            return true
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            handleWebError(errorCode = errorCode)
        }


        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            error?.let {
                handleWebError(errorCode = it.errorCode)
            }
        }

        private fun handleWebError(errorCode:Int){
            when (errorCode) {
                ERROR_AUTHENTICATION -> {
                    showDialog("인증 오류가 발생하였습니다. 다시 시도해주세요.")
                }
                ERROR_BAD_URL -> {
                    showDialog("URL 주소가 잘못되었습니다. 다시 시도해주세요.")
                }
                ERROR_CONNECT -> {
                    showDialog("연결 도중 에러가 발생하였습니다. 다시 시도해주세요.")
                }
                ERROR_FAILED_SSL_HANDSHAKE -> {
                    showDialog("SSL 인증 실패 에러가 발생하였습니다. 다시 시도해주세요.")
                }
                ERROR_TIMEOUT -> {
                    showDialog("연결 시간이 초과했습니다. 다시 시도해주세요.")
                }
                ERROR_TOO_MANY_REQUESTS -> {
                    showDialog("너무 많은 요청이 들어왔습니다. 다시 시도해주세요.")
                }
                ERROR_UNKNOWN -> {
                    showDialog("알 수 없는 에러가 발생했습니다. 다시 시도해주세요.")
                }
            }
        }
    }

}
