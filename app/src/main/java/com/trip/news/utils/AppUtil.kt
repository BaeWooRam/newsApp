package com.trip.news.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log

object AppUtil {
    fun getVersionName(context: Context): String{
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