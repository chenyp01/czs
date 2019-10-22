package com.beasts.czs.controller;

import com.alibaba.druid.util.StringUtils;
import com.beasts.czs.common.RedisUtil;
import com.beasts.czs.model.po.User;
import com.beasts.czs.model.vo.UserVo;
import com.beasts.czs.service.UserService;
import com.beasts.czs.utils.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户信息管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    private final String userLoginInfo = "USER_LOGIN_INFO";

    @GetMapping("/select_all")
    public List<User> selectAll(){
        return userService.selectAll();
    }

    @RequestMapping("/login")
    public ResObject login(HttpServletRequest request, String userName, String pwd, String userType) throws Exception{
        ResObject resObject = new ResObject();
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(userType)){
            return ResObject.error(-1,"参数错误");
        }
        UserVo userVo = userService.login(userName, pwd, userType);
        if (userVo == null){
            return ResObject.error(-2,"用户名或密码错误");
        }else{
            // 保存用户信息30天
            String sessionId = request.getSession().getId();
            redisUtil.set(userLoginInfo+"_"+sessionId,userVo,30 * 24 * 60 * 60);
            resObject.put("userInfo",userVo);
            resObject.put("sessionId",sessionId);
        }
        return resObject;
    }

    @RequestMapping("/register")
    public ResObject register(HttpServletRequest request, User user) throws Exception{
        UserVo userVo = userService.getUserInfoByUserName(user.getUserName(),user.getUserType());
        if (userVo != null) return ResObject.error("用户名已存在");
        userService.saveUser(user);

        // 保存用户信息30天
        String sessionId = request.getSession().getId();
        redisUtil.set(userLoginInfo+"_"+sessionId,userVo,30 * 24 * 60 * 60);
        ResObject resObject = new ResObject();
        resObject.put("userInfo",user);
        resObject.put("sessionId",sessionId);
        return resObject;
    }

}
