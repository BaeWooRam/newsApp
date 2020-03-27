package com.trip.news.base.view

import com.trip.news.model.NetworkState

interface BaseView {
    fun showDialog(message: String)
    fun progressON(type: NetworkState.ProgressType)
    fun progressOFF()
    fun isNetwork()
}
