package com.beasts.czs.controller;

import com.alibaba.druid.util.StringUtils;
import com.beasts.czs.common.RedisUtil;
import com.beasts.czs.model.po.BookCatalogInfo;
import com.beasts.czs.model.po.BookContent;
import com.beasts.czs.model.po.BookInfo;
import com.beasts.czs.model.po.User;
import com.beasts.czs.model.vo.UserVo;
import com.beasts.czs.service.BookService;
import com.beasts.czs.utils.ResObject;
import com.beasts.czs.utils.SpiderUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 小说管理
 *
 * @program manage
 * @createDate: 2019/10/7
 * @author: chenyp
 */
@RestController
@RequestMapping("/book")
public class BookController {

    Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisUtil redisUtil;

    private final String userLoginInfo = "USER_LOGIN_INFO";

    /**
     * 查询书本列表
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ResObject search(String key) throws Exception {
        if(StringUtils.isEmpty(key.trim())) return ResObject.error("请输入查询内容");
        ResObject resObject = new ResObject();
        List<BookInfo> qidianList = SpiderUtils.searchQidianBookListByKey(key);
        List<BookInfo> cywxList = SpiderUtils.searchCywxBookListByKey(key);
        // bookService.batchSaveBookInfo(qidianList);
        // bookService.batchSaveBookInfo(cywxList);
        resObject.put("qidianList", qidianList);
        resObject.put("cywxList", cywxList);
        return resObject;
    }

    /**
     * 查询小说章节目录
     * @param bookId
     * @return
     * @throws Exception
     */
    @RequestMapping("/searchCatalog")
    public ResObject searchCatalog(String bookId, String sourceBookId,String source) throws Exception {
        if(StringUtils.isEmpty(sourceBookId)) return ResObject.error("小说ID为空");
        ResObject resObject = new ResObject();
        List<BookCatalogInfo> catalog = bookService.queryCatalogList(bookId, sourceBookId,source);
        resObject.put("catalog", catalog);
        return resObject;
    }

    /**
     * 查询小说章节内容
     * @param detailUrl
     * @return
     * @throws Exception
     */
    @RequestMapping("/searchDetail")
    public ResObject searchDetail(String detailUrl, String source) throws Exception {
        if(StringUtils.isEmpty(detailUrl.trim())) return ResObject.error("章节地址为空");
        ResObject resObject = new ResObject();
        BookContent bookDetail = bookService.searchBookContent(detailUrl, source);
        resObject.put("bookDetail", bookDetail);
        return resObject;
    }

    /**
     * 查询数据源
     * @param bookName
     * @param author
     * @param catalogName
     * @return
     * @throws Exception
     */
    @RequestMapping("/querySourceList")
    public ResObject querySourceList(String bookName, String author, String catalogName) throws Exception {
        ResObject resObject = new ResObject();
        resObject.put("sources",SpiderUtils.querySourceList(bookName, author, catalogName));
        return resObject;
    }

    /**
     * 翻页
     * @param bookId        书本ID
     * @param catalogName   章节名称
     * @param source        数据源
     * @param type          翻页类型: -1.上一章; 1.下一章;
     * @return
     * @throws Exception
     */
    @RequestMapping("/nextCatalog")
    public ResObject nextCatalog(String bookId, String catalogName, String source, String type) throws Exception {
        ResObject resObject = new ResObject();
        if(StringUtils.isEmpty(bookId) || StringUtils.isEmpty(catalogName) || StringUtils.isEmpty(source) || StringUtils.isEmpty(type)){
            return ResObject.error("参数错误");
        }
        List<BookCatalogInfo> catalogs = bookService.queryCatalogList(null, bookId,source);
        BookCatalogInfo catalog = null;
        for (int i = 0; i < catalogs.size(); i++){
            if(catalogName.trim().equals(catalogs.get(i).getCatalogName().trim())){
                int index = i;
                if("-1".equals(type)){
                    if(i > 0){
                        index--;
                    }else{
                        return ResObject.error("已经是第一章了");
                    }
                }else if("1".equals(type)) {
                    if (i < catalogs.size() - 1){
                        index++;
                    }else{
                        return ResObject.error("已经是最后一章了");
                    }
                }
                catalog = catalogs.get(index);
            }
        }
        resObject.put("catalog", catalog);
        /*if(catalog != null){
            BookContent bookDetail = bookService.searchBookContent(catalog.getDetailUrl(), source);
            resObject.put("bookDetail", bookDetail);
        }*/
        return resObject;
    }

    /**
     * 收藏
     * @param request
     * @param bookName
     * @param author
     * @return
     * @throws Exception
     */
    @RequestMapping("/like")
    public ResObject like(HttpServletRequest request, String bookName, String author) throws Exception{
        String sessionId = request.getSession().getId();
        UserVo userVo = (UserVo) redisUtil.get(userLoginInfo+"_"+sessionId);
        if (userVo != null){
            bookService.like(bookName, author, userVo.getId()+"");
            return ResObject.ok();
        }else{
            return ResObject.error("请先登录");
        }
    }

    /**
     * 取消收藏
     * @param request
     * @param bookId
     * @return
     * @throws Exception
     */
    @RequestMapping("/cancelLike")
    public ResObject cancelLike(HttpServletRequest request, String bookId) throws Exception{
        String sessionId = request.getSession().getId();
        UserVo userVo = (UserVo) redisUtil.get(userLoginInfo+"_"+sessionId);
        if (userVo != null){
            bookService.cancelLike(bookId, userVo.getId()+"");
            return ResObject.ok();
        }else{
            return ResObject.error("请先登录");
        }
    }

}
