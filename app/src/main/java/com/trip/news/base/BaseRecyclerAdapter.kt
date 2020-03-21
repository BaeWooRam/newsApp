package com.trip.news.base

import androidx.recyclerview.widget.RecyclerView
import com.trip.news.model.rss.news.News

abstract class BaseRecyclerAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseAdapter<T> {

    var itemList = ArrayList<T>()

    override fun addItem(item: T) {
        itemList.add(item)

        notifyItemInserted(itemList.size - 1)
    }

    override fun addItemList(itemList: List<T>) {
        for (product in itemList)
            this.itemList.add(product)

        notifyDataSetChanged()
    }

    override fun setItemList(itemList: List<T>) {
        this.itemList = itemList as ArrayList<T>

        notifyDataSetChanged()
    }

    override fun clearItemList() {
        itemList.clear()
        notifyDataSetChanged()
    }
}