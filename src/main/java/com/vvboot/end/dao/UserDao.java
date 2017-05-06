package com.vvboot.end.dao;

import com.vvboot.end.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhuxl@paxsz.com
 * @time 2017/5/4
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}
