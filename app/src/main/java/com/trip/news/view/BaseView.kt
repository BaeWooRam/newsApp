package com.trip.news.view

interface BaseView {
    interface Dialog {
        fun showDialog(message: String?)
    }

    interface ProgressBar {
        fun progressON()
        fun progressSET(message: String)
        fun progressOFF()
    }
}
