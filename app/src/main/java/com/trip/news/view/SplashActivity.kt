package com.trip.news.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.trip.news.R
import com.trip.news.base.BaseActivity
import com.trip.news.view.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity:BaseActivity(R.layout.activity_splash) {
    private val handler = Handler()
    private val splashTime = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNewIcon()

        handler.postDelayed({
            goToNewsListActivity()
        },splashTime)
    }

    private fun initNewIcon(){
        Glide.with(this)
            .load(R.drawable.icon_news)
            .apply(RequestOptions.circleCropTransform())
            .into(icon_news)
    }

    private fun goToNewsListActivity(){
        val intent = Intent(this, NewsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}