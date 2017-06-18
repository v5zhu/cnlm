package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Overtime;
import com.vvboot.end.busi.params.PageParam;

import java.util.List;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeDao {
    void addOvertime(Overtime overtime);

    List<Overtime> pageList(PageParam pageParam);
}
