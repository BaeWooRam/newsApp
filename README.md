
1주차
===

## 03-18
챌린지 공고를 보고 도전!

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
