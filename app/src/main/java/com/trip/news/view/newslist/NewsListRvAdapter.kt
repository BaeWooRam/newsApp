package com.trip.news.view.newslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trip.news.R
import com.trip.news.base.BaseRecyclerAdapter
import com.trip.news.databinding.ItemNewsBinding
import com.trip.news.model.rss.news.News
import com.trip.news.view.newdetail.NewsDetailActivity
import com.trip.news.view.newslist.NewsListActivity.Companion.INTENT_ACTIVITY_NEWS_DETAIL


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
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(news: News) {
            binding.news = news
            binding.root.setOnClickListener(this)
            addKeywordView(news.keyword)
        }

        override fun onClick(view: View?) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                goToNewsDetailActivity(itemList[pos].link)
            }
        }

        private fun goToNewsDetailActivity(link:String){
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putExtra(INTENT_ACTIVITY_NEWS_DETAIL,link)
            context.startActivity(intent)
        }

        private fun addKeywordView(keywordList:List<String>){
            val rvKeyword = binding.root.findViewById<RecyclerView>(R.id.rv_keyword)
            val adapter =  NewsKeywordRvAdapter(inflater)
            rvKeyword.adapter = adapter

            adapter.itemList = keywordList as ArrayList<String>
        }
    }
}