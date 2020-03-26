package com.trip.news.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.trip.news.base.ProgressType

sealed class NetworkState<out T> {
    object Init : NetworkState<Nothing>()
    class Loading(val type:ProgressType) : NetworkState<Nothing>()
//    class Success<out T>(val item: T) : NetworkState<T>()
    class Error(val throwable: Throwable?) : NetworkState<Nothing>()
}