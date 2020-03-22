package com.trip.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.trip.news.base.BaseViewModel
import com.trip.news.model.NetworkState
import com.trip.news.model.retrofit.RssService
import com.trip.news.model.rss.news.News
import com.trip.news.model.rss.news.NewsContentsParser
import com.trip.news.utils.NetworkUtil
import com.trip.news.view.newslist.NewsListActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class NewsListViewModel(
    private val rssService: RssService,
    private val newsContentsParser: NewsContentsParser
) : BaseViewModel() {
    var targetActivity:NewsListActivity? = null

    private var currentNewsState: NetworkState<List<News>> by Delegates.observable(
        NetworkState.Init,
        { _: KProperty<*>, _: NetworkState<List<News>>, newState: NetworkState<List<News>> ->
            when (newState) {
                is NetworkState.Init -> {

                }

                is NetworkState.Loading -> {
                    targetActivity?.progressON()
                }

                is NetworkState.Success<List<News>> -> {
                    targetActivity?.onUpdateNews(newState.item)
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
                GlobalScope.launch(Dispatchers.IO) {
                    newsContentsParser.clearNewsList()
                    newsContentsParser.parserNewsContents(it.channel.item)
                    currentNewsState = NetworkState.Success(newsContentsParser.newsList)
                }
            }, Consumer {
                currentNewsState = NetworkState.Error(it)
            })
    }
}