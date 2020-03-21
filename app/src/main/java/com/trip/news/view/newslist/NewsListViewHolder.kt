package com.trip.news.view.newslist

import androidx.recyclerview.widget.RecyclerView
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News
import com.trip.news.viewmodel.NewsListViewModel

class NewsListViewHolder(
    private val viewModel: NewsListViewModel,
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        //TODO BIND 할것들
    }
}