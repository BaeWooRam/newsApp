
1주차
===

챌린지 공고를 보고 도전!
차근차근 생각하면서 만들어보자

## 03-18
상세 요구사항
1. 스플래쉬 화면
2. 뉴스 리스트
3. 뉴스 상세보기

뉴스내용은 RSS로!

RSS가 무엇인가? 모르겠으면 찾아봐야지!

![image](https://user-images.githubusercontent.com/41356481/76941768-f5728280-693f-11ea-97b4-41575196062f.png)


간단하게 말씀드리면 해당 사이트에 올라오는 글들을 자동으로 받아보는 것을 말합니다. 영어로는 Real Simple Syndication(직역하면 ‘매우 간단한 배급’ 정도 되겠다)이다.

RSS 이용방법
RSS는 사용자가 해당 사이트의 RSS주소를 가지게 됩니다. 이걸 RSS 프로그램에 입력하면 자동으로 해당 사이트의 컨텐츠를 긁어오게 되는 것이라고 한다.

아 아래 설명이 어느정도 이해가 가고 저 정보들을 긁어와서 리스트로 뿌려주면 되겠구나 생각이 든다!
![image](https://user-images.githubusercontent.com/41356481/76942185-aa0ca400-6940-11ea-992f-9230264390b4.png)

http통신을 해서 Rss를 들고오면
DocumentBuilderFactory, DocumentBuilder 이용해서 XML 파싱을 해보기로 하였다

근데 테스트 환경해서 그런가 영어로 나와서 수정해야겠당 ㅎㅎ;

## 03-19
영어로 나오는 이유 = https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko 이렇게 해줘야할 것 같다.
그리고 찾아보다가 retrofit으로 하면 더 가독성이 좋아질것같아서 바꿔보기로 했다!

짜짠 바꾸고 나니깐 가독성이 훨씬 좋아진 것 같다.

이제 정보를 가져오기 위해 다시 한번 정리 해보았다.
![image](https://user-images.githubusercontent.com/41356481/77139562-d39d0b00-6ab9-11ea-8cdd-55d18278da2f.png) 
먼저, 뉴스 본문 분석을 진행해보도록 하기로 했다!


## 03-20
오늘 뉴스 본문을 크롬 브라우저에서 "페이지 소스 보기"를 통해 소스를 보았는데
![image](https://user-images.githubusercontent.com/41356481/77022391-e0900080-69cc-11ea-94b2-91533b60cf0b.png)
mata 데이터를 들고 와야겠네? 여러 페이지를 비교해 보았다. 그결과,
![image](https://user-images.githubusercontent.com/41356481/77141250-8de34100-6abf-11ea-900c-e57aa8fb7a4b.png)

위와 같은 구조로 되어있다. 이걸 paser을 해봐야겠다~
Retrofit으로 paser을 하려고 하니 뉴스마다 Service를 만들어줘야하는 문제가 있어서
Jsoup으로 분석을 해보려고 한다.

```
    fun parserNewsContents(rssItemList: List<Item>){
        try {
            for(rssItem in rssItemList){
                val url = rssItem.link

                if(isUrl(url)){
                    val doc = Jsoup.connect(url).get()
                    val description =  getNewsContents(doc.select("meta[property=og:description]"))
                    val imageURL = getNewsContents(doc.select("meta[property=og:image]"))
                    val keywordList = parserKeyword(description)

                    val news = News(description = description, link = url, imageURL = imageURL, keyword = keywordList)
                    println("Create News $news")
                    newsList.add(news)
                }
            }
        } catch (e: Exception){
            println("parserNewsContents Function Error! ${e.message}")
        }
    }

    private fun getNewsContents(elements: Elements):String{
        return if(elements.size > 0){
            return elements[0].attr("content")
        }else{
            ""
        }
    }
```
