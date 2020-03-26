package com.trip.news.base

interface BaseView {
    fun showDialog(message: String)
    fun progressON(type: ProgressType)
    fun progressOFF()
    fun isNetwork()
}
