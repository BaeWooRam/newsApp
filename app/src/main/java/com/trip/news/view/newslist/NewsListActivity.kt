package com.trip.news.view.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trip.news.R
import com.trip.news.model.rss.news.News

class NewsListActivity : AppCompatActivity(), NewsListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError(e: Throwable?) {

    }

    override fun onLoading() {

    }

    override fun onInit() {

    }
}
