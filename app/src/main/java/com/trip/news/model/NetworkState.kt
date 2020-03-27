package com.trip.news.model


sealed class NetworkState<out T> {
    object Init : NetworkState<Nothing>()
    class Loading(val type: ProgressType) : NetworkState<Nothing>()
    class Error(val throwable: Throwable?) : NetworkState<Nothing>()


    //ProgressType
    enum class ProgressType(private val value:Int) {
        LOADING_RSS(2), LOADING_NEWS(1), LOADING_NONE(0);

        fun getValue() = value
    }
}