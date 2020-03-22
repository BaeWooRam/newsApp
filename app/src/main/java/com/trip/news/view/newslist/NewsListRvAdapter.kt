package com.trip.news.view.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R
import com.trip.news.base.BaseRecyclerAdapter
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News


class NewsListRvAdapter(
    context: Context
) : BaseRecyclerAdapter<News>(){
    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNewsBinding>(
            inflater,
            R.layout.item_news,
            parent,
            false
        )

        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        (holder as NewsListViewHolder).bind(item)
    }


    inner class NewsListViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.news = news
            //TODO KEYWORD
            val keywordLayout = binding.root.findViewById<LinearLayout>(R.id.keywordLayout)

        }
    }
}