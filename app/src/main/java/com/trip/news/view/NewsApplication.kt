package com.trip.news.view

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialog
import com.trip.news.R
import com.trip.news.model.NetworkState
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

    fun progressON(activity: Activity?, type: NetworkState.ProgressType) {
        if (activity == null || activity.isFinishing)
            return

        when(type){
            NetworkState.ProgressType.LOADING_RSS -> showProgress(activity, R.layout.layout_progress_rss)
            NetworkState.ProgressType.LOADING_NEWS -> showProgress(activity, R.layout.layout_progress_news)
            else -> throw Throwable("Error ProgressType!")
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