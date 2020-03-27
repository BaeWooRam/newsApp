package com.trip.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.trip.news.base.viewmodel.BaseViewModel
import com.trip.news.model.NetworkState
import com.trip.news.model.news.NewsDataFactory
import com.trip.news.model.api.RssService
import com.trip.news.model.news.News
import com.trip.news.model.news.NewsContentsParser
import com.trip.news.model.news.NewsContentsParser.Companion.PAGE_NEWS_SIZE
import com.trip.news.view.newslist.NewsListActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
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

    private var rssData: LiveData<PagedList<News>>? = null
        set(value) {
            field = value
            rssData?.removeObservers(targetActivity!!)
        }

    var currentNewsState: NetworkState<News> by Delegates.observable(
        NetworkState.Init,
        { _: KProperty<*>, _: NetworkState<News>, newState: NetworkState<News> ->
            when (newState) {
                is NetworkState.Init -> {
                    targetActivity?.progressOFF()
                }

                is NetworkState.Loading -> {
                    targetActivity?.progressON(newState.type)
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
            .doOnTerminate {
                currentNewsState = NetworkState.Init
            }
            .doOnSubscribe {
                disposable.add(it)
                currentNewsState = NetworkState.Loading(NetworkState.ProgressType.LOADING_RSS)
            }
            .subscribe(Consumer {
                //Paging DataSourceFactory 생성
                val dataSourceFactory = NewsDataFactory(
                    newsContentsParser = newsContentsParser,
                    rss = it,
                    viewModel = this
                )

                //Paging LiveData 생성
                rssData = LivePagedListBuilder(
                    dataSourceFactory,
                    pagedListConfig
                ).build()

                //LiveData 업데이트 관찰
                rssData?.observe(targetActivity!!, Observer<PagedList<News>> { item ->
                    targetActivity!!.onUpdateNews(item)
                    currentNewsState = NetworkState.Init
                })

            }, Consumer {
                currentNewsState = NetworkState.Error(it)
            })
    }

}