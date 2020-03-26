package com.trip.news.view.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.trip.news.model.news.News

class NewsPageListAdapter(context: Context) : PagedListAdapter<News, NewsViewHolder>(REPO_COMPARATOR) {
    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent, inflater)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val repoItem = getItem(position)
        repoItem?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                    oldItem == newItem
        }
    }
}