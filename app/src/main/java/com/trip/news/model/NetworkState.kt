package com.trip.news.model

import com.trip.news.base.type.ProgressType

sealed class NetworkState<out T> {
    object Init : NetworkState<Nothing>()
    class Loading(val type: ProgressType) : NetworkState<Nothing>()
//    class Success<out T>(val item: T) : NetworkState<T>()
    class Error(val throwable: Throwable?) : NetworkState<Nothing>()
}