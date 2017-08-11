package me.cnlm.busi.dao.common;

import me.cnlm.busi.entity.common.Oppose;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by PAX on 2017/4/13.
 */
@Mapper
public interface OpposeMybatisDao {
    int addOppose(Oppose oppose);
}
