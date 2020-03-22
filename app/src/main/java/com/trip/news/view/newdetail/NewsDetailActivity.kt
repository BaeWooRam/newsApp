package com.trip.news.view.newdetail

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.trip.news.R
import com.trip.news.base.BaseActivity
import com.trip.news.view.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : BaseActivity(R.layout.activity_news_detail){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWebView()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_news.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            wv_news.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun initWebView() {
        val webViewSetting = wv_news.settings
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true

        wv_news.webChromeClient = WebChromeClient()
        wv_news.webViewClient = WebViewClientClass()
        wv_news.setNetworkAvailable(true)

        val link = intent.getStringExtra(NewsListActivity.INTENT_ACTIVITY_NEWS_DETAIL)
        wv_news.loadUrl(link)
    }

    inner class WebViewClientClass : WebViewClient() {
        //페이지 이동
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.i(javaClass.simpleName, "UrlLoading : $url")
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.i(javaClass.simpleName, "onPageFinished")
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.i(javaClass.simpleName, "onPageStarted")
            super.onPageStarted(view, url, favicon)
        }
    }

}
