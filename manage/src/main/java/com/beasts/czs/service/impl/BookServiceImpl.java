package com.beasts.czs.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.beasts.czs.dao.BookInfoDao;
import com.beasts.czs.dao.UserBookLikeDao;
import com.beasts.czs.model.BeanTools;
import com.beasts.czs.model.po.*;
import com.beasts.czs.model.vo.BookInfoVo;
import com.beasts.czs.service.BookService;
import com.beasts.czs.utils.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 书本信息管理
 *
 * @program manage
 * @createDate: 2019/10/10
 * @author: chenyp
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private UserBookLikeDao userBookLikeDao;

    @Override
    public void saveBookInfo(BookInfo bookInfo) {
        BookInfoVo queryInfo = new BookInfoVo();
        queryInfo.setBookName(bookInfo.getBookName());
        queryInfo.setAuthor(bookInfo.getAuthor());
        queryInfo.setSource(bookInfo.getSource());
        List<BookInfoVo> bookInfos = bookInfoDao.bookInfoList(queryInfo);
        bookInfo.setCreateTime(new Timestamp(new Date().getTime()));
        if(bookInfos.size() > 0){
            bookInfo.setId(bookInfos.get(0).getId());
            bookInfoDao.updateBookInfo(bookInfo);
        }else{
            bookInfoDao.saveBookInfo(bookInfo);
        }
    }

    @Override
    public void batchSaveBookInfo(List<BookInfo> bookInfos) {
        for (BookInfo bookInfo : bookInfos){
            saveBookInfo(bookInfo);
        }
    }

    @Override
    public void batchSaveBookCatalog(List<BookCatalogInfo> catalogInfos) {
        for (BookCatalogInfo catalogInfo : catalogInfos){
            catalogInfo.setCreateTime(new Timestamp(new Date().getTime()));
        }
    }

    @Override
    public void saveBookContent(BookContent bookContent) {

    }

    @Override
    public List<BookCatalogInfo> queryCatalogList(String bookId, String sourceBookId, String source) {
        List<BookCatalogInfo> catalog = null;
        if (SpiderUtils.sourceIds[0].equals(source)){
            catalog = SpiderUtils.searchQidianBookCatalogById(bookId, sourceBookId);
        }else if(SpiderUtils.sourceIds[1].equals(source)){
            catalog = SpiderUtils.searchCywxBookCatalogById(bookId, sourceBookId);
        }
        return catalog;
    }

    @Override
    public BookContent searchBookContent(String detailUrl, String source) {
        BookContent bookDetail = null;
        if (SpiderUtils.sourceIds[0].equals(source)){
            bookDetail = SpiderUtils.searchQidianBookDetailByUrl(detailUrl);
        }else if(SpiderUtils.sourceIds[1].equals(source)){
            bookDetail = SpiderUtils.searchCywxBookDetailByUrl(detailUrl);
        }
        return bookDetail;
    }

    @Override
    public boolean like(String bookName, String author, String userId) {
        BookInfoVo bookInfoVo = new BookInfoVo();
        bookInfoVo.setBookName(bookName);
        bookInfoVo.setAuthor(author);
        int count = bookInfoDao.countBookInfo(bookInfoVo);
        BookInfo bookInfo = null;
        if (count > 0){
            List<BookInfoVo> bookInfos = bookInfoDao.bookInfoList(bookInfoVo);
            if(bookInfos.size() > 0) bookInfo = BeanTools.getPo(bookInfos.get(0));
        }
        UserBookLike userBookLike = new UserBookLike();
        if (bookInfo == null){
            bookInfo = SpiderUtils.searchQidianBookInfo(bookName, author);
            if (bookInfo == null) {
                bookInfo = SpiderUtils.searchCywxBookInfo(bookName, author);
            }
        }
        if(bookInfo == null) return false;
        userBookLike.setBookId(bookInfo.getId());
        userBookLike.setUserId(Integer.valueOf(userId));
        userBookLike.setBookName(bookInfo.getBookName());
        userBookLike.setAuthor(bookInfo.getAuthor());
        int likeCount = userBookLikeDao.countUserBookLike(BeanTools.getVo(userBookLike));
        if(likeCount == 0){
            userBookLike.setCreateTime(new Timestamp(new Date().getTime()));
            userBookLikeDao.saveUserBookLike(userBookLike);
        }
        return true;
    }

    @Override
    public void cancelLike(String bookId, String userId) {
        if(StringUtils.isEmpty(bookId) || StringUtils.isEmpty(userId)) return;
        userBookLikeDao.deleteUserBookLike(bookId, userId);
    }

}
