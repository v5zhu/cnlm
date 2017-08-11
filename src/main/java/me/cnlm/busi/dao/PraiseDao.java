package me.cnlm.busi.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhuxl@paxsz.com on 2017/8/11.
 */
@Mapper
public interface PraiseDao {
    int findTotalPraise();

    int increase();
}
