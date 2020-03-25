package com.trip.news.view.newslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R
import com.trip.news.databinding.ItemKeywordBinding
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News
import com.trip.news.view.newdetail.NewsDetailActivity
import com.trip.news.view.newslist.NewsListActivity.Companion.INTENT_ACTIVITY_NEWS_DETAIL

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        if(news.title.isNotEmpty()){
            binding.news = news
            binding.root.setOnClickListener{
                goToNewsDetailActivity(news.link)
            }

            addKeywordView(news.keyword)
        }
    }

    private fun goToNewsDetailActivity(link: String) {
        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(INTENT_ACTIVITY_NEWS_DETAIL, link)
        context.startActivity(intent)
    }

    private fun addKeywordView(keywordList: List<String>) {
        val rvKeyword = binding.root.findViewById<RecyclerView>(R.id.rv_keyword)
        val adapter = NewsKeywordRvAdapter(context)
        rvKeyword.adapter = adapter

        adapter.itemList = keywordList as ArrayList<String>
    }

    companion object {
        fun create(parent: ViewGroup, inflater: LayoutInflater): NewsViewHolder {
            val binding = DataBindingUtil.inflate<ItemNewsBinding>(
                inflater,
                R.layout.item_news,
                parent,
                false
            )
            return NewsViewHolder(binding, parent.context)
        }
    }
}