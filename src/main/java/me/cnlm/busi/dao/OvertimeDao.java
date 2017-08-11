package me.cnlm.busi.dao;

import me.cnlm.core.commons.PageParam;
import me.cnlm.busi.entity.Overtime;

import java.util.List;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeDao {
    void addOvertime(Overtime overtime);

    List<Overtime> pageList(PageParam pageParam);
}
