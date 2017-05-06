package com.vvboot.end.busi.dao;


import com.vvboot.end.busi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface UserMybatisDao {
    int register(User user);

    int updateUser(User user);

    String checkPhone(String phone);

    int perfectUserInfo(Map params);

    User findByToken(String uid);

    int checkIsRegisteredByPhone(String phone);

    User findByUserId(Long userId);

    List<User> findAllUsers();
}
