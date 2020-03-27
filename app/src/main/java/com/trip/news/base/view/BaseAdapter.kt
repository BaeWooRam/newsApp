package com.trip.news.base.view

interface BaseAdapter<T> {
    fun addItem(item: T)
    fun addItemList(itemList: List<T>)
    fun setItemList(itemList: List<T>)
    fun clearItemList()
}