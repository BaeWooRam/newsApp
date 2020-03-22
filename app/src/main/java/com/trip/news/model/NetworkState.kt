package com.trip.news.model

sealed class NetworkState<out T> {
    object Init : NetworkState<Nothing>()
    object Loading : NetworkState<Nothing>()
    class Success<out T>(val item: T) : NetworkState<T>()
    class Error(val throwable: Throwable?) : NetworkState<Nothing>()
}