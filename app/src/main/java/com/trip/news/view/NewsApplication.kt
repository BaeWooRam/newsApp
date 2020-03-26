package com.trip.news.view

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatDialog
import com.trip.news.R
import com.trip.news.base.ProgressType
import com.trip.news.model.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication:Application(){
    companion object{
        private var instance: NewsApplication? = null

        fun getGlobalApplicationContext(): NewsApplication{
            checkNotNull(instance) { "This Application does not inherit GlobalApplication" }
            return instance as NewsApplication
        }
    }

    private var progressDialog: AppCompatDialog? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        //koin 초기화
        startKoin {
            androidLogger()
            androidContext(getGlobalApplicationContext())
            modules(appModule)
        }
    }

    fun progressON(activity: Activity?, type:ProgressType) {
        if (activity == null || activity.isFinishing)
            return

        when(type){
            ProgressType.LOADING_RSS -> showProgress(activity, R.layout.layout_progress_rss)
            ProgressType.LOADING_NEWS -> showProgress(activity, R.layout.layout_progress_news)
        }
    }

    private fun showProgress(activity: Activity?, @LayoutRes layoutID:Int) {
        progressOFF()

        progressDialog = AppCompatDialog(activity)
        progressDialog?.setCancelable(false)
        progressDialog?.setContentView(layoutID)

        val window = progressDialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        progressDialog?.show()
    }

    fun progressOFF() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

}