package com.vvboot.end.busi.dao.common;

import com.vvboot.end.busi.entity.common.Oppose;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by PAX on 2017/4/13.
 */
@Mapper
public interface OpposeMybatisDao {
    int addOppose(Oppose oppose);
}
