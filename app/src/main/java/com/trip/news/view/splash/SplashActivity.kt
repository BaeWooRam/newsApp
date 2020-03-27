package com.trip.news.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.trip.news.R
import com.trip.news.base.view.BaseActivity
import com.trip.news.utils.AppUtil
import com.trip.news.view.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity: BaseActivity(R.layout.activity_splash) {
    private val handler = Handler()
    private val splashTime = 1300L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity(){
        //앱 버전 이름
        version.text = AppUtil.getVersionName(this)

        //1.3초가 지나고 NewsListActivity 이동
        handler.postDelayed({
            goToNewsListActivity()
        },splashTime)
    }

    private fun goToNewsListActivity(){
        val intent = Intent(this, NewsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }

}