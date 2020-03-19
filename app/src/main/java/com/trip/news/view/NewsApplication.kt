package com.trip.news.view

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.trip.news.R
import com.trip.news.model.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication:Application(){
    companion object{
        private var instance: NewsApplication? = null


        fun getGlobalApplicationContext(): NewsApplication{
            checkNotNull(instance) { "This Application does not inherit com.kakao.GlobalApplication" }
            return instance as NewsApplication
        }

    }

    private var progressDialog: AppCompatDialog? = null

    override fun onCreate() {
        super.onCreate()

        //koin 초기화
        startKoin {
            androidLogger()
            androidContext(getGlobalApplicationContext())
            modules(appModule)
        }
    }

    fun progressON(activity: Activity?, message: String?) {

        if (activity == null || activity.isFinishing) {
            return
        }


        if (progressDialog != null && progressDialog!!.isShowing) {
            progressSET(message?:"")
        } else {

            progressDialog = AppCompatDialog(activity)
            progressDialog!!.setCancelable(false)
            progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog!!.setContentView(R.layout.progress_loading)
            progressDialog!!.show()

        }

        val progressMessage = progressDialog!!.findViewById<TextView>(R.id.tv_progress_message)
        if (!TextUtils.isEmpty(message)) {
            progressMessage?.text = message
        }


    }


    fun progressSET(message: String) {

        if (progressDialog == null || !progressDialog!!.isShowing) {
            return
        }


        val progressMessage = progressDialog!!.findViewById<TextView>(R.id.tv_progress_message)

        if (!TextUtils.isEmpty(message)) {
            progressMessage?.text = message
        }

    }

    fun progressOFF() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

}