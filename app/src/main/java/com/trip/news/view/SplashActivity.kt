package com.trip.news.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.trip.news.R
import com.trip.news.base.BaseActivity
import com.trip.news.view.newslist.NewsListActivity

class SplashActivity:BaseActivity(R.layout.activity_splash) {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler.postDelayed({
            goToNewsListActivity()
        },2000)
    }

    private fun goToNewsListActivity(){
        val intent = Intent(this, NewsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}