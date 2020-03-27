package com.trip.news.base

import com.trip.news.base.type.ProgressType

interface BaseView {
    fun showDialog(message: String)
    fun progressON(type: ProgressType)
    fun progressOFF()
    fun isNetwork()
}
