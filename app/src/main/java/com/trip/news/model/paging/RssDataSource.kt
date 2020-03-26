package com.trip.news.model.paging

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.paging.PositionalDataSource
import com.trip.news.base.ProgressType
import com.trip.news.model.NetworkState
import com.trip.news.model.rss.Item
import com.trip.news.model.rss.Rss
import com.trip.news.model.news.News
import com.trip.news.model.news.NewsContentsParser
import com.trip.news.viewmodel.NewsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class RssDataSource(
    rss: Rss,
    private val newsContentsParser: NewsContentsParser,
    private val viewModel: NewsListViewModel
) : PositionalDataSource<News>() {
    companion object{
        
    }
    private val tag = javaClass.simpleName
    private val itemQueue = rss.channel.getItemQueue()
    private val handler = Handler(Looper.getMainLooper()){
        when(it.what){

        }
        viewModel.currentNewsState = NetworkState.Loading(type = ProgressType.LOADING_NEWS)
        return@Handler true
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<News>
    ) {
//        Log.i(
//            tag,
//            "Initial Loading, rss : ${rss.channel.item.size}, itemQueue : ${itemQueue.size}, start: ${params.requestedStartPosition}, size: ${params.requestedLoadSize}"
//        )
        handler.sendEmptyMessage(0)
        val rssItem = getRssItem()
        val data = newsContentsParser.parserNewsContents(rssItem)
        callback.onResult(data, 0, NewsContentsParser.PAGE_NEWS_SIZE)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
//        Log.i(
//            tag,
//            "Range Loading, rss : ${rss.channel.item.size}, itemQueue : ${itemQueue.size}, start: ${params.startPosition}, size: ${params.loadSize}"
//        )
        handler.sendEmptyMessage(0)
        val rssItem = getRssItem()
        val data = newsContentsParser.parserNewsContents(rssItem)
        callback.onResult(data)

    }

    /**
     * Queue에서 Page 아이템 갯수만큼 가져온다.
     */
    private fun getRssItem(): List<Item> {
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