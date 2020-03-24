package com.trip.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.trip.news.base.BaseViewModel
import com.trip.news.model.NetworkState
import com.trip.news.model.paging.RssDataFactory
import com.trip.news.model.retrofit.RssService
import com.trip.news.model.rss.Rss
import com.trip.news.model.rss.news.News
import com.trip.news.model.rss.news.NewsContentsParser
import com.trip.news.model.rss.news.NewsContentsParser.Companion.PAGE_NEWS_SIZE
import com.trip.news.view.newslist.NewsListActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class NewsListViewModel(
    private val rssService: RssService,
    private val newsContentsParser: NewsContentsParser
) : BaseViewModel() {
    var targetActivity: NewsListActivity? = null

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(PAGE_NEWS_SIZE)
        .setPageSize(PAGE_NEWS_SIZE)
        .build()

    var rssData:LiveData<PagedList<News>>? = null

    private var currentNewsState: NetworkState<Unit> by Delegates.observable(
        NetworkState.Init,
        { _: KProperty<*>, _: NetworkState<Unit>, newState: NetworkState<Unit> ->
            when (newState) {
                is NetworkState.Init -> {

                }

                is NetworkState.Loading -> {
                    targetActivity?.progressON()
                }

                is NetworkState.Success<Unit> -> {
                    targetActivity?.progressOFF()
                }

                is NetworkState.Error -> {
                    targetActivity?.onNetworkError(newState.throwable)
                    targetActivity?.progressOFF()
                }
            }
        })

    fun getNews() {
        val work = rssService.getKrNewsList()

        work.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                disposable.add(it)
                currentNewsState = NetworkState.Loading
            }
            .subscribe(Consumer {
                currentNewsState = NetworkState.Success(Unit)
                val dataSourceFactory = RssDataFactory(newsContentsParser, it)

                rssData = LivePagedListBuilder(
                    dataSourceFactory,
                    pagedListConfig
                ).build()

                rssData?.observe(targetActivity!!, Observer<PagedList<News>> { item ->
                    targetActivity!!.onUpdateNews(item)
                })
            }, Consumer {
                currentNewsState = NetworkState.Error(it)
            })
    }

}