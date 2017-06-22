package net.leebao.open.busi.dao;

import net.leebao.open.busi.params.PageParam;
import net.leebao.open.busi.entity.Overtime;

import java.util.List;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeDao {
    void addOvertime(Overtime overtime);

    List<Overtime> pageList(PageParam pageParam);
}
