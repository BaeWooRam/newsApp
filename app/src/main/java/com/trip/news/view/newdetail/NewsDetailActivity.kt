package com.trip.news.view.newdetail

import android.os.Bundle
import com.trip.news.R
import com.trip.news.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : BaseActivity(R.layout.activity_news_detail), NewsDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initWebView(){
        wv_news.
    }

    override fun onError(e: Throwable?) {
        e?.also {
            showDialog("에러가 발생하였습니다. ${it.message}")
        }
    }

    override fun onLoading() {
        progressON()
    }

    override fun onInit() {
        progressOFF()
    }
}
