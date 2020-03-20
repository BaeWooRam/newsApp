package com.trip.news.model.rss.news

import com.trip.news.model.rss.Item
import com.trip.news.utils.StringUtil.replaceSpecialCharacters
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * RSS 아이템의 본문 분석해서 News 클래스로 만드는 역할
 */
class NewsContentsParser {
    companion object {
        const val KEYWORD_COUNT_MAX = 3
    }

    private val tag = javaClass.simpleName
    val newsList = ArrayList<News>()

    /**
     * og:image, og:description 가져오기
     */
    fun parserNewsContents(rssItemList: List<Item>) {
        for (rssItem in rssItemList) {
            val url = rssItem.link

            if (isUrl(url)) {
                val doc = Jsoup.connect(url).get()
                val description = getNewsContents(doc.select("meta[property=og:description]"))
                val imageURL = getNewsContents(doc.select("meta[property=og:image]"))
                val keywordList = parserKeyword(description)

                val news = News(
                    description = description,
                    link = url,
                    imageURL = imageURL,
                    keyword = keywordList
                )
//                    Log.i(tag, "Create News $news")
                println("Create News $news")
                newsList.add(news)
            }
        }
    }

    /**
     *  meta에서 image, description contents 가져오기
     */
    private fun getNewsContents(elements: Elements): String {
        return if (elements.size > 0) {
            return elements[0].attr("content")
        } else {
            ""
        }
    }

    /**
     * og:description을 분석해서 ketword 가져오기
     *
     *  조건 : 조사,어미 무시하고 띄어쓰기로 구분하고 등장 빈도수 높은 3건 추출
     */
    fun parserKeyword(description: String): List<String> {
        val replacedDescription = replaceSpecialCharacters(description, "")
        println("description : $description \nreplacedDescription : $replacedDescription")
        val keywordToken = StringTokenizer(replacedDescription, " ")
        val keywordCountHash: HashMap<String, Int> = HashMap()

        while (keywordToken.hasMoreTokens()) {
            val target = keywordToken.nextToken()
            val count = getCountMatchKeyword(target, replacedDescription)
            keywordCountHash[target] = count
            println("$target : $count")
        }

        return getFrequentKeyword(keywordCountHash)
    }

    /**
     * 조사,어미 무시하고 띄어쓰기로 구분하고 키워드 등장 빈도수 측정
     */
    private fun getCountMatchKeyword(keyword: String, description: String): Int {
        var count = 0
        val patternString = "\\b($keyword)\\b"
        val pattern = Pattern.compile(patternString)
        val matcher: Matcher = pattern.matcher(description)

        while (matcher.find())
            count++

        return count
    }


    /**
     * 키워드 오름차순 정렬하고 최대치까지 내보낸다.
     */
    private fun getFrequentKeyword(keywordCountHash: HashMap<String, Int>): List<String> {
        val tempList = keywordCountHash.toList()

        //오름차순 정렬하기
        Collections.sort(tempList) { o1, o2 ->
            val comparision: Int = (o1.second - o2.second) * -1
            return@sort if (comparision == 0) o1.first.compareTo(o2.first) else comparision
        }

        //최대치까지 내보낸다.
        val keywordList = ArrayList<String>()

        if (tempList.isNotEmpty()){
            for (index in 0 until KEYWORD_COUNT_MAX) {
                keywordList.add(tempList[index].first)
            }
        }

        return keywordList
    }

    fun isUrl(text: String?): Boolean {
        //URL 패턴 확인
        val p =
            Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?[a-zA-Z0-9./]+$")
        val m = p.matcher(text)
        if (m.matches()) return true

        //URL 클래스로 생성 및 체크
        var u: URL? = null

        u = try {
            URL(text)
        } catch (e: MalformedURLException) {
            return false
        }

        try {
            u.toURI()
        } catch (e: URISyntaxException) {
            return false
        }
        return true
    }
}