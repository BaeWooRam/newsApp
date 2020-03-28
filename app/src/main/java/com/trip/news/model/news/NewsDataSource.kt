package com.trip.news.model.news

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.paging.PositionalDataSource
import com.trip.news.model.NetworkState
import com.trip.news.model.rss.Item
import com.trip.news.model.rss.Rss
import com.trip.news.viewmodel.NewsListViewModel
import kotlin.collections.ArrayList

class NewsDataSource(
    rss: Rss,
    private val newsContentsParser: NewsContentsParser,
    private val viewModel: NewsListViewModel
) : PositionalDataSource<News>() {

    private val tag = javaClass.simpleName
    private val itemQueue = rss.channel.getItemQueue()
    private val handler = Handler(Looper.getMainLooper()){
        when(it.what){
            NetworkState.ProgressType.LOADING_NEWS.getValue() -> {
                viewModel.currentNewsState = NetworkState.Loading(type = NetworkState.ProgressType.LOADING_NEWS)
            }

            NetworkState.ProgressType.LOADING_RSS.getValue() -> {
                viewModel.currentNewsState = NetworkState.Loading(type = NetworkState.ProgressType.LOADING_NEWS)
            }

            NetworkState.ProgressType.LOADING_NONE.getValue() ->{
                viewModel.currentNewsState = NetworkState.Init
            }
        }

        return@Handler true
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<News>
    ) {
//        Log.i( tag, "Initial Loading, itemQueue : ${itemQueue.size}, start: ${params.requestedStartPosition}, size: ${params.requestedLoadSize}" )
        handler.sendEmptyMessage(NetworkState.ProgressType.LOADING_NEWS.getValue())
        val rssItem = getRssItem()
        val data = newsContentsParser.parserNewsContents(rssItem)
        handler.sendEmptyMessage(NetworkState.ProgressType.LOADING_NONE.getValue())
        callback.onResult(data, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
//        Log.i(tag, "Range Loading, itemQueue : ${itemQueue.size}, start: ${params.startPosition}, size: ${params.loadSize}" )
        handler.sendEmptyMessage(NetworkState.ProgressType.LOADING_NEWS.getValue())
        val rssItem = getRssItem()
        val data = newsContentsParser.parserNewsContents(rssItem)
        handler.sendEmptyMessage(NetworkState.ProgressType.LOADING_NONE.getValue())
        callback.onResult(data)

    }

    /**
     * Queue에서 Page 아이템 갯수만큼 가져온다.
     */
    private fun getRssItem(): ArrayList<Item> {
        val itemList = ArrayList<Item>()
        var count = 0

        while (itemQueue.isNotEmpty()) {
            if (count == NewsContentsParser.PAGE_NEWS_SIZE)
                return itemList
            else {
                val temp = itemQueue.poll()

                if (temp != null) {
                    itemList.add(temp)
                    count++
                } else
                    return itemList
            }
        }

        return itemList
    }
}