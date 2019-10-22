package com.beasts.czs.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.beasts.czs.model.po.BookCatalogInfo;
import com.beasts.czs.model.po.BookContent;
import com.beasts.czs.model.po.BookInfo;
import com.beasts.czs.model.vo.BookInfoVo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫工具
 *
 * @program manage
 * @createDate: 2019/10/8
 * @author: chenyp
 */
public class SpiderUtils {

    private static Logger logger = Logger.getLogger(SpiderUtils.class);

    // 起点中文网:https://www.qidian.com
    // 尘缘文学网:http://m.15cy.com/search.php
    public static String[] sourceIds = new String[]{"QD","CYWX"};
    public static String[] sourceNames = new String[]{"起点中文网","尘缘文学网"};

    /**
     * 查询数据源
     * @param bookName
     * @param author
     * @param catalogName
     * @return
     */
    public static List<Map<String, String>> querySourceList(String bookName, String author, String catalogName){
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < sourceIds.length; i++){
            Map<String, String> map = new HashMap<>();
            map.put("id",sourceIds[i]);
            map.put("name",sourceNames[i]);
            list.add(map);
        }
        List<Map<String, String>> list2 = new ArrayList<>();
        for (Map<String, String> map : list){
            boolean isMap = true;
            String bookId = "";
            String detailUrl = "";
            if (!StringUtils.isEmpty(bookName)){
                List<BookInfo> bookInfos = new ArrayList<>();
                if(sourceIds[0].equals(map.get("id"))){
                    bookInfos = searchQidianBookListByKey(bookName);
                }else if(sourceIds[1].equals(map.get("id"))){
                    bookInfos = searchCywxBookListByKey(bookName);
                }
                if(bookInfos.size() > 0){
                    boolean isBook = false;     // 此数据源是否存在这本书
                    for (BookInfo bookInfo : bookInfos){
                        if (bookName.trim().equals(bookInfo.getBookName().trim())){
                            if(!StringUtils.isEmpty(author)){
                                if(author.equals(bookInfo.getAuthor())){
                                    isBook = true;
                                    bookId = bookInfo.getBookId();
                                    break;
                                }
                            }else{
                                isBook = true;
                                bookId = bookInfo.getBookId();
                                break;
                            }
                        }
                    }
                    if(!isBook){
                        // list.remove(map);
                        // break;
                        isMap = false;
                    }
                }else{
                    // list.remove(map);
                    // break;
                    isMap = false;
                }

                // 章节目录
                if(isMap && !StringUtils.isEmpty(catalogName)){
                    for (BookInfo bookInfo : bookInfos){
                        List<BookCatalogInfo> catalogInfos = new ArrayList<>();
                        if(sourceIds[0].equals(map.get("id"))){
                            catalogInfos = searchQidianBookCatalogById(bookInfo.getId()+"",bookInfo.getBookId());
                        }else if(sourceIds[1].equals(map.get("id"))){
                            catalogInfos = searchCywxBookCatalogById(bookInfo.getId()+"",bookInfo.getBookId());
                        }
                        boolean isCatalog = false;  // 此数据源的这本书是否存在此章节
                        for (BookCatalogInfo catalogInfo : catalogInfos){
                            if(catalogName.trim().equals(catalogInfo.getCatalogName().trim())){
                                isCatalog = true;
                                detailUrl = catalogInfo.getDetailUrl();
                                break;
                            }
                        }
                        if(!isCatalog){
                            // list.remove(map);
                            // break;
                            isMap = false;
                        }
                    }
                }
            }
            if(!StringUtils.isEmpty(bookId)){
                map.put("bookId",bookId);
            }
            if(!StringUtils.isEmpty(detailUrl)){
                map.put("detailUrl",detailUrl);
            }
            if(isMap){
                list2.add(map);
            }
        }
        return list2;
    }

    /* ************************************ 起点start ************************************ **/

    /**
     * 查询起点书本列表
     * @param key
     * @return
     */
    public static List<BookInfo> searchQidianBookListByKey(String key){
        if(StringUtils.isEmpty(key)) return new ArrayList<>();
        String url = "https://www.qidian.com/search?kw=" + key;
        return searchQidianBookList(HttpsUtils.doGet(url),key);
    }

    /**
     * 查询小说章节目录
     * @param bookId
     * @return
     */
    public static List<BookCatalogInfo> searchQidianBookCatalogById(String bookId, String sourceBookId){
        String url = "https://book.qidian.com/info/" + sourceBookId + "#Catalog";
        return searchQidianBookCatalog(HttpsUtils.doGet(url), bookId);
    }

    /**
     * 查询小说章节内容
     * @param url
     * @return
     */
    public static BookContent searchQidianBookDetailByUrl(String url){
        return searchQidianBookDetail(HttpsUtils.doGet(url));
    }

    /**
     * 查询小说书本信息
     * @param bookName
     * @param author
     * @return
     */
    public static BookInfo searchQidianBookInfo(String bookName, String author){
        if (StringUtils.isEmpty(bookName) || StringUtils.isEmpty(author)) return null;
        List<BookInfo> bookInfos = searchQidianBookListByKey(bookName);
        BookInfo bi = null;
        for (BookInfo bookInfo : bookInfos){
            if(bookName.equals(bookInfo.getBookName()) && author.equals(bookInfo.getAuthor())){
                bi = bookInfo;
                break;
            }
        }
        return bi;
    }

    /**
     * 查询书本列表
     * @param htmlStr
     * @param key
     * @return
     */
    private static List<BookInfo> searchQidianBookList(String htmlStr, String key){
        Document document = Jsoup.parse(htmlStr);
        Element element = document.getElementById("result-list");
        Elements liElements = element.select("li");
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i = 0; i < liElements.size(); i++){
            BookInfo bookInfo = new BookInfo();
            Element liElement = liElements.get(i);
            // 书名
            String bookName = getTextByElement(liElement, ".book-mid-info,h4","text");
            if(bookName.indexOf(key) == -1) break;
            bookInfo.setBookName(bookName);
            // 书ID
            bookInfo.setBookId(getTextByElement(liElement, "","data-bid"));
            // 封面
            String bookImgUrl = "";
            bookImgUrl = getTextByElement(liElement, ".book-img-box,img","src");
            if(!StringUtils.isEmpty(bookImgUrl)) bookImgUrl = "http:" + bookImgUrl;
            bookInfo.setBookImg(bookImgUrl);
            // 作者
            bookInfo.setAuthor(getTextByElement(liElement, ".book-mid-info,.author,a","text"));
            // 书本类型
            bookInfo.setType(getTextByElement(liElement, ".book-mid-info,.author,a|2","text"));
            // 书本状态
            bookInfo.setStatus(getTextByElement(liElement, ".book-mid-info,.author,span","text"));
            // 简介
            bookInfo.setRemark(getTextByElement(liElement, ".book-mid-info,.intro","text"));
            // 最新章节
            String newCatalog = getTextByElement(liElement, ".book-mid-info,.update,a","text");
            if(!StringUtils.isEmpty(newCatalog)) newCatalog = newCatalog.replace("最新更新","").trim();
            bookInfo.setNewCatalog(newCatalog);
            // 最新章节更新时间
            bookInfo.setUpdateTime(getTextByElement(liElement, ".book-mid-info,.update,span","text"));
            // 总字数
            bookInfo.setWordNum(getTextByElement(liElement, ".book-right-info,.total,span","text"));
            bookInfo.setSource(sourceIds[0]);
            bookInfo.setSourceName(sourceNames[0]);
            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }

    /**
     * 查询小说章节目录
     * @param htmlStr
     */
    private static List<BookCatalogInfo> searchQidianBookCatalog(String htmlStr, String bookId){
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.getElementsByClass("volume-wrap");
        if(elements.size() == 0) return new ArrayList<>();
        Elements liElements = getElistByElement(elements.get(0),"li");
        List<BookCatalogInfo> list = new ArrayList<>();
        for (int i = 0; i < liElements.size(); i++) {
            BookCatalogInfo catalogInfo = new BookCatalogInfo();
            Element liElement = liElements.get(i);
            // 书本ID
            if (!StringUtils.isEmpty(bookId)){
                try {
                    catalogInfo.setBookId(Integer.valueOf(bookId));
                }catch (Exception e){}
            }
            // 章节详情链接
            String detailUrl = getTextByElement(liElement,"a","data-cid");
            if(!StringUtils.isEmpty(detailUrl)) detailUrl = "http:" + detailUrl;
            catalogInfo.setDetailUrl(detailUrl);
            // 目录名称
            catalogInfo.setCatalogName(getTextByElement(liElement,"a","text"));
            catalogInfo.setSource(sourceIds[0]);
            catalogInfo.setSourceName(sourceNames[0]);
            list.add(catalogInfo);
        }
        return list;
    }

    /**
     * 查询小说章节内容
     * @param htmlStr
     * @return
     */
    private static BookContent searchQidianBookDetail(String htmlStr){
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.getElementsByClass("main-text-wrap");
        Element element = elements.get(0);
        BookContent bookContent = new BookContent();
        // 章节名称
        bookContent.setCatalogName(getTextByElement(element, ".text-head,h3", "text"));
        // 章节内容
        bookContent.setContent(getTextByElement(element, ".read-content","html"));
        bookContent.setSource(sourceIds[0]);
        bookContent.setSourceName(sourceNames[0]);
        return bookContent;
    }

    /* ************************************ 起点end ************************************ **/

    /* ************************************ 尘缘文学网start ************************************ **/

    /**
     * 查询书本列表
     * @param key
     * @return
     */
    public static List<BookInfo> searchCywxBookListByKey(String key) {
        if(StringUtils.isEmpty(key)) return new ArrayList<>();
        try {
            key = URLEncoder.encode(key, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://m.15cy.com/search.php?type=articlename&s="+key;
        return searchCywxBookList(HttpsUtils.doGet(url,"GBK"));
    }

    /**
     * 查询小说章节目录
     * @param bookId    书本ID
     * @return
     */
    public static List<BookCatalogInfo> searchCywxBookCatalogById(String bookId, String sourceBookId){
        if(StringUtils.isEmpty(sourceBookId) || sourceBookId.length() < 2) return null;
        String type = sourceBookId.substring(0,2);
        List<BookCatalogInfo> list = new ArrayList<>();
        int pageNum = 1;
        int count = -1;
        while (count < list.size()){
            count = list.size();
            String url = "http://m.15cy.com/html/" + type + "/" + sourceBookId + "_" + pageNum + "/";
            System.out.println("获取["+sourceNames[1]+"]章节目录：" + url);
            searchCywxBookCatalog(list, HttpsUtils.doGet(url,"GBK"), bookId);
            pageNum++;
        }
        return list;
    }

    /**
     * 查询小说章节内容
     * @param url
     * @return
     */
    public static BookContent searchCywxBookDetailByUrl(String url){
        if(StringUtils.isEmpty(url)) return null;
        BookContent bookContent = searchCywxBookDetail(HttpsUtils.doGet(url,"GBK"));
        // 对目录里只有一章，而内容里有可能分成多章的情况进行处理
        String[] arr = url.split("/");
        String pageStr = arr[arr.length - 1].replace(".html","");
        int pageNum = 2;
        String content = "";
        String oldContent = "";
        while (true){
            String pageNew = pageStr + "_" + pageNum + ".html";
            String urlNew = url.replace(arr[arr.length - 1],pageNew);
            BookContent tmp = searchCywxBookDetail(HttpsUtils.doGet(urlNew,"GBK"));
            if(tmp != null && !StringUtils.isEmpty(tmp.getContent().trim())){
                if(oldContent.equals(tmp.getContent().trim())){
                    break;
                }else{
                    oldContent = tmp.getContent().trim();
                    content += oldContent;
                    if(oldContent.length() < 50){
                        break;
                    }
                }
                pageNum++;
            }else{
                break;
            }
        }
        bookContent.setContent(bookContent.getContent() + content);
        return bookContent;
    }

    /**
     * 查询小说书本信息
     * @param bookName
     * @param author
     * @return
     */
    public static BookInfo searchCywxBookInfo(String bookName, String author){
        if (StringUtils.isEmpty(bookName) || StringUtils.isEmpty(author)) return null;
        List<BookInfo> bookInfos = searchCywxBookListByKey(bookName);
        BookInfo bi = null;
        for (BookInfo bookInfo : bookInfos){
            if(bookName.equals(bookInfo.getBookName()) && author.equals(bookInfo.getAuthor())){
                bi = bookInfo;
                break;
            }
        }
        return bi;
    }

    private static List<BookInfo> searchCywxBookList(String htmlStr) {
        Document document = Jsoup.parse(htmlStr);
        Element element = document.getElementsByClass("searchcon").get(0);
        Elements ulElements = element.select("ul");
        List<BookInfo> list = new ArrayList<>();
        for (int i = 0; i < ulElements.size(); i++) {
            BookInfo bookInfo = new BookInfo();
            Element ulElement = ulElements.get(i);
            // 书ID
            String bookId = getTextByElement(ulElement,"h3,a","href");
            bookId = bookId.replace("/book/","").replace(".html","");
            bookInfo.setBookId(bookId);
            // 书名
            bookInfo.setBookName(getTextByElement(ulElement,"h3","text"));
            // 作者
            String authorName = getTextByElement(ulElement,"li|2","text");
            if(!StringUtils.isEmpty(authorName)) authorName = authorName.replace("作者：","");
            bookInfo.setAuthor(authorName);
            // 最新章节
            bookInfo.setNewCatalog(getTextByElement(ulElement,"li|3,span,a","text"));
            // 最新章节更新时间
            Element liElement = getElistByElement(ulElement,"li|3").get(0);
            liElement.getElementsByTag("span").remove();
            String updateTime = liElement.text().replace("(","").replace(")","");
            bookInfo.setUpdateTime(updateTime);
            // 总字数
            bookInfo.setSource(sourceIds[1]);
            bookInfo.setSourceName(sourceNames[1]);
            list.add(bookInfo);
        }
        return list;
    }

    private static List<BookCatalogInfo> searchCywxBookCatalog(List<BookCatalogInfo> list, String htmlStr, String bookId) {
        Document document = Jsoup.parse(htmlStr);
        Elements es = document.getElementsByClass("chapterlist");
        Element element = null;
        if(es.size() > 0) element = es.get(0);
        if(element == null) return null;
        Elements liElements = getElistByElement(element,"ul.clist,li");
        for (int i = 0; i < liElements.size(); i++) {
            BookCatalogInfo catalogInfo = new BookCatalogInfo();
            Element liElement = liElements.get(i);
            // 书本ID
            if(!StringUtils.isEmpty(bookId)){
                try {
                    catalogInfo.setBookId(Integer.valueOf(bookId));
                }catch (Exception e){}
            }
            // 章节详情链接
            String detailUrl = getTextByElement(liElement,"a","href");
            if(!StringUtils.isEmpty(detailUrl)) detailUrl = "http://m.15cy.com" + detailUrl;
            catalogInfo.setDetailUrl(detailUrl);
            // 目录名称
            catalogInfo.setCatalogName(getTextByElement(liElement,"a","text"));
            catalogInfo.setSource(sourceIds[1]);
            catalogInfo.setSourceName(sourceNames[1]);
            list.add(catalogInfo);
        }
        return list;
    }

    /**
     * 查询小说章节内容
     * @param htmlStr
     * @return
     */
    private static BookContent searchCywxBookDetail(String htmlStr){
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.getElementsByClass("chapter");
        Element element = elements.get(0);
        BookContent bookContent = new BookContent();
        // 章节名称
        bookContent.setCatalogName(getTextByElement(element, ".title,h2", "text"));
        // 章节内容
        bookContent.setContent(getTextByElement(element, "#content","html"));
        bookContent.setSource(sourceIds[1]);
        bookContent.setSourceName(sourceNames[1]);
        return bookContent;
    }

    /* ************************************ 尘缘文学网end ************************************ **/

    /**
     * 获取节点下所需的信息
     * @param element   节点
     * @param str       索引key
     * @param type      最终获取值的方式
     * @return
     */
    public static String getTextByElement(Element element, String str, String type){
        return getTextByElement(element, str, type, 0);
    }

    /**
     * 获取节点下所需的信息
     * @param element   节点
     * @param str       索引key
     * @param type      最终获取值的方式
     * @param index     遍历的层级
     * @return
     */
    public static String getTextByElement(Element element, String str, String type, int index){
        String[] arr = str.split(",");
        Element _e = null;
        if(!StringUtils.isEmpty(str) && arr.length > 0){
            if(arr[index].indexOf("|") >= 0){
                String selectKey = arr[index].split("\\|")[0];
                String _index = arr[index].split("\\|")[1];
                int i = Integer.valueOf(_index) - 1;
                Elements _es = element.select(selectKey);
                if(_es.size() > i) _e = _es.get(i);
            }else{
                Elements _es = element.select(arr[index]);
                if(_es.size() > 0) _e = _es.get(0);
            }
        }else{
            _e = element;
        }
        if(_e != null){
            if(arr.length - 1 == index){
                String result = "";
                if("text" == type){
                    result = _e.text();
                }else if("html" == type){
                    result = _e.html();
                }else {
                    result = _e.attr(type);
                }
                return result;
            }else{
                return getTextByElement(_e, str, type, ++index);
            }
        }else{
            return "";
        }
    }

    public static Elements getElistByElement(Element element, String str){
        return getElistByElement(element, str, 0);
    }

    public static Elements getElistByElement(Element element, String str, int index){
        String[] arr = str.split(",");
        Element _e = null;
        if(!StringUtils.isEmpty(str) && arr.length > 0){
            if(index == arr.length - 1){
                Elements _es = null;
                if(arr[index].indexOf("|") >= 0){
                    String selectKey = arr[index].split("\\|")[0];
                    String _index = arr[index].split("\\|")[1];
                    int i = Integer.valueOf(_index) - 1;
                    _es = element.select(selectKey);
                    if(_es.size() > i){
                        Element e = _es.get(i);
                        Elements es = new Elements();
                        es.add(e);
                        _es = es;
                    }
                }else{
                    _es = element.select(arr[index]);
                }
                return _es;
            }else{
                if(arr[index].indexOf("|") >= 0){
                    String selectKey = arr[index].split("\\|")[0];
                    String _index = arr[index].split("\\|")[1];
                    int i = Integer.valueOf(_index) - 1;
                    Elements _es = element.select(selectKey);
                    if(_es.size() > i) _e = _es.get(i);
                }else{
                    Elements _es = element.select(arr[index]);
                    if(_es.size() > 0) _e = _es.get(0);
                }
                return getElistByElement(_e,str,++index);
            }
        }else{
            return null;
        }
    }

    /**
     * 打印list
     * @param list
     */
    public static void printList(List<Map<String,String>> list){
        if(list != null){
            for (int i = 0; i < list.size(); i++){
                System.out.println("=========================================================");
                for (String key : list.get(i).keySet()){
                    System.out.println(key + " : " + list.get(i).get(key));
                    logger.info(key + " : " + list.get(i).get(key));
                }
            }
        }
    }

    /**
     * 打印map
     * @param map
     */
    public static void printMap(Map<String,String> map){
        System.out.println("=========================================================");
        for (String key : map.keySet()){
            System.out.println(key + " : " + map.get(key));
            logger.info(key + " : " + map.get(key));
        }
    }

    public static void main(String[] args){

    }

}
