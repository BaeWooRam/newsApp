package com.trip.news.base

import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

interface BaseAdapter<T> {
    fun addItem(item: T)
    fun addItemList(itemList: List<T>)
    fun setItemList(itemList: List<T>)
    fun clearItemList()
}