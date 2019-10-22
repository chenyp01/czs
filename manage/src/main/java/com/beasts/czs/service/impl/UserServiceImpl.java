package com.beasts.czs.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.beasts.czs.common.RedisUtil;
import com.beasts.czs.dao.UserDao;
import com.beasts.czs.model.po.User;
import com.beasts.czs.model.vo.UserVo;
import com.beasts.czs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 用户信息管理
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    public List<User> selectAll() {

        /*List<User> userList = null;
        List<?> list = redisUtil.lGet("userList",0,-1);

        if(list != null && list.size() != 0){
            userList = (List<User>)list;
            return userList;
        }

        userList= userMapper.selectAll();
        redisUtil.lSet("userList",userList);*/

        return null;
    }

    @Override
    public void saveUser(User user) {
        user.setCreateTime(new Timestamp(new Date().getTime()));
        userDao.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int delUser(List<Long> ids) {
        return 0;
    }

    @Override
    public UserVo getUserInfoByUserName(String userName, String userType) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userType)) return null;
        UserVo userVo = new UserVo();
        userVo.setUserName(userName);
        userVo.setUserType(userType);
        List<UserVo> userVos = userDao.userList(userVo);
        if (userVos.size() > 0) return userVos.get(0);
        return null;
    }


    @Override
    public UserVo login(String userName, String pwd, String userType) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(userType)) return null;
        UserVo userVo = new UserVo();
        userVo.setUserName(userName);
        userVo.setPassword(pwd);
        userVo.setUserType(userType);
        userVo.setStatus("1");
        List<UserVo> users = userDao.userList(userVo);
        if (users.size() > 0){
            userVo = users.get(0);
            return userVo;
        }
        return null;
    }
}
