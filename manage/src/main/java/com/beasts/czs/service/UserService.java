package com.beasts.czs.service;

import com.beasts.czs.model.po.User;
import com.beasts.czs.model.vo.UserVo;

import java.util.List;

public interface UserService {

    public List<User> selectAll();

    /**
     * 保存用户信息
     * @param user
     */
    public void saveUser(User user);

    public int updateUser(User user);

    public int delUser(List<Long> ids);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @param userType
     * @return
     */
    public UserVo getUserInfoByUserName(String userName, String userType);

    /**
     * 登录
     * @param userName
     * @param pwd
     * @param userType
     * @return
     */
    public UserVo login(String userName, String pwd, String userType);

}
