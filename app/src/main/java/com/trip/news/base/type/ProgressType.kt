package com.trip.news.base.type

enum class ProgressType(private val value:Int) {
    LOADING_RSS(2), LOADING_NEWS(1), LOADING_NONE(0);

    fun getValue() = value
}