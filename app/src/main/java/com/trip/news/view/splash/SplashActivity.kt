package com.trip.news.view.splash

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trip.news.R
import com.trip.news.base.BaseActivity
import com.trip.news.view.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity:BaseActivity(R.layout.activity_splash) {
    private val handler = Handler()
    private val splashTime = 1300L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()

        handler.postDelayed({
            goToNewsListActivity()
        },splashTime)
    }

    private fun initActivity(){
        //아이콘 원형 마스킹
        Glide.with(this)
            .load(R.drawable.icon_news)
            .apply(RequestOptions.circleCropTransform())
            .into(icon_news)

        //앱 버전 이름
        version.text = getVersionName(this)
    }

    private fun goToNewsListActivity(){
        val intent = Intent(this, NewsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }

    private fun getVersionName(context: Context): String{
        var versionName = "Unknown"
        val packageInfo: PackageInfo

        try {
            packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(javaClass.simpleName, "getVersionInfo :" + e.message)
        }

        return versionName
    }
}