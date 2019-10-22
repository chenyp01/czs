package com.beasts.czs.model;

import com.beasts.czs.model.po.BookInfo;
import com.beasts.czs.model.po.UserBookLike;
import com.beasts.czs.model.vo.BookInfoVo;
import com.beasts.czs.model.vo.UserBookLikeVo;
import org.springframework.beans.BeanUtils;

/**
 * bean工具类
 *
 * @program manage
 * @createDate: 2019/10/20
 * @author: chenyp
 */
public class BeanTools {

    public static BookInfo getPo(BookInfoVo vo){
        BookInfo po = new BookInfo();
        BeanUtils.copyProperties(vo, po);
        return po;
    }

    public static BookInfoVo getVo(BookInfo po){
        BookInfoVo vo = new BookInfoVo();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public static UserBookLike getPo(UserBookLikeVo vo){
        UserBookLike po = new UserBookLike();
        BeanUtils.copyProperties(vo, po);
        return po;
    }

    public static UserBookLikeVo getVo(UserBookLike po){
        UserBookLikeVo vo = new UserBookLikeVo();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

}
