package com.trip.news.view.newslist

import android.os.Bundle
import androidx.paging.PagedList
import com.trip.news.R
import com.trip.news.base.BaseActivity
import com.trip.news.databinding.ActivityNewsListBinding
import com.trip.news.model.rss.news.News
import com.trip.news.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.activity_news_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListActivity : BaseActivity(), NewsListlView {
    companion object{
        const val INTENT_ACTIVITY_NEWS_DETAIL = "NEWS_DETAIL"
    }

    private val viewModel by viewModel<NewsListViewModel>()
    private var binding: ActivityNewsListBinding? = null
    private var adapter:NewsPageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init
        binding = setDataBindingContentView(R.layout.activity_news_list)
        viewModel.targetActivity = this

        initRecyclerView()
        initSwipe()

        viewModel.getNews()
    }

    private fun initSwipe(){
        swipe.setOnRefreshListener{
            adapter?.currentList?.clear()
            viewModel.getNews()

            swipe.isRefreshing = false
        }
    }

    private fun initRecyclerView(){
        adapter = NewsPageListAdapter(this)

        rv_news.adapter = adapter
        rv_news.addItemDecoration(NewsItemDecoration(this))
    }

    override fun onNetworkError(e: Throwable?) {
        e?.also {
            showDialog("에러가 발생하였습니다. ${it.message}")
        }
    }

    override fun onUpdateNews(newsList: PagedList<News>) {
        adapter?.submitList(newsList)
    }
}
