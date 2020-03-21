package com.trip.news.model.rss

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

@Deprecated("현재 Retrofit으로 바꾸는 중")
class RssModel {
    var requestURL: String = ""

    fun parseRss(inputStream: InputStream) {
        val factory =
            DocumentBuilderFactory.newInstance()

        val builder = factory.newDocumentBuilder()
        val document: Document = builder.parse(inputStream)

        val itemList: ArrayList<Item> = parseDocument(document)

        println("파싱된 아이템의 갯수 : " + itemList.size)
    }

    fun parseDocument(document: Document): ArrayList<Item> {
        val elem: Element = document.documentElement
        val nodeList: NodeList = elem.getElementsByTagName("item")
        val itemList: ArrayList<Item> = ArrayList<Item>()

        for (index in 0 until nodeList.length) {
            val item = parseItemNode(nodeList, index)

            itemList.add(item)
        }
        return itemList
    }

    fun parseItemNode(nodeList: NodeList, index: Int): Item {
        val elem = nodeList.item(index) as Element

        val titleElem = elem.getElementsByTagName("title").item(0) as Element

        val linkElem = elem.getElementsByTagName("link").item(0) as Element

        val descriptionElem = elem.getElementsByTagName("description").item(0) as Element

//        val imageElem = elem.getElementsByTagName("image").item(0) as Element
//
//        val dcdateElem = elem.getElementsByTagName("dc:date").item(0) as Element


        val titleChild = titleElem.firstChild

        if (titleChild != null) {
            val title = titleChild.nodeValue
            println("title : $title")
        }

        val linkChild = linkElem.firstChild

        if (linkChild != null) {
            var link = linkChild.nodeValue
            println("link : $link")
        }

        val descriptionChild = descriptionElem.firstChild

        if (descriptionChild != null) {
            var description = descriptionChild.nodeValue
            println("description : $description")
        }
        /*var image = ""

        if (imageElem != null) {
            val firstChild = imageElem.firstChild

            if (firstChild != null) {
                image = firstChild.nodeValue
            }
        }

        var dcDate = ""

        if (dcdateElem != null) {
            val firstChild = dcdateElem.firstChild

            if (firstChild != null) {
                dcDate = firstChild.nodeValue
            }
        }*/


        return Item("", "")
    }

    fun getRssItem(): List<Item> {
        return listOf()
    }

    fun test() {
        try {
            val url = URL(requestURL)

            val conn =
                url.openConnection() as HttpURLConnection

            conn.doInput = true
            conn.doOutput = true
            conn.connectTimeout = 15000 // 15초


            // 15초
            val resCode = conn.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                val input = conn.inputStream
                parseRss(input)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}