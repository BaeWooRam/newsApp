package com.trip.news.view.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R
import com.trip.news.base.BaseRecyclerAdapter
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News

@Deprecated("페이징 라이브러리로 바꿈")
class NewsListRvAdapter(
    private val context: Context
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

        return NewsViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        (holder as NewsViewHolder).bind(item)
    }
}