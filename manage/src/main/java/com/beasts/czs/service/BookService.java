package com.beasts.czs.service;

import com.beasts.czs.model.po.BookCatalogInfo;
import com.beasts.czs.model.po.BookContent;
import com.beasts.czs.model.po.BookInfo;
import com.beasts.czs.model.po.User;

import java.util.List;

public interface BookService {

    public void saveBookInfo(BookInfo bookInfo);

    public void batchSaveBookInfo(List<BookInfo> bookInfos);

    public void batchSaveBookCatalog(List<BookCatalogInfo> catalogInfos);

    public void saveBookContent(BookContent bookContent);

    /**
     * 查询小说章节列表
     * @param bookId
     * @param sourceBookId
     * @param source
     * @return
     */
    public List<BookCatalogInfo> queryCatalogList(String bookId, String sourceBookId, String source);

    /**
     * 查询章节内容
     * @param detailUrl
     * @param source
     * @return
     */
    public BookContent searchBookContent(String detailUrl, String source);

    /**
     * 收藏
     * @param bookName
     * @param author
     * @param userId
     * @return
     */
    public boolean like(String bookName, String author, String userId);

    /**
     * 取消收藏
     * @param bookId
     * @param userId
     */
    public void cancelLike(String bookId, String userId);

}
