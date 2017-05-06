package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Toutiao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LONG on 2017/3/22.
 */
@Mapper
public interface ToutiaoMybatisDao {
    int insertToutiaoInBatch(List<Toutiao> toutiaos);

    List<Toutiao> overview(Map params);

    int deleteToutiaoBefore10(Date date);

    List<Toutiao> findAll();

    void increaseApprovalAmount(String id);

    void increaseOpposeAmount(String id);

    Toutiao findById(String objectId);

    int findApprovalAmountById(String objectId);

    int findOpposeAmountById(String objectId);
}
