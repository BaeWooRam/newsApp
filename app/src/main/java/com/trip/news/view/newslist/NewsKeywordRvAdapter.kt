package com.trip.news.view.newslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R
import com.trip.news.base.BaseRecyclerAdapter
import com.trip.news.databinding.ItemKeywordBinding
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News
import com.trip.news.view.newdetail.NewsDetailActivity

class NewsKeywordRvAdapter(
    context: Context
) : BaseRecyclerAdapter<String>(){
    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemKeywordBinding>(
            inflater,
            R.layout.item_keyword,
            parent,
            false
        )

        return NewsKeywordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        (holder as NewsKeywordViewHolder).bind(item)
    }

    inner class NewsKeywordViewHolder(private val binding: ItemKeywordBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(keyword: String) {
            binding.keyword = keyword
        }
    }
}