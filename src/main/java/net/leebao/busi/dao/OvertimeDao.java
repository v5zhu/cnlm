package net.leebao.busi.dao;

import net.leebao.core.commons.PageParam;
import net.leebao.busi.entity.Overtime;

import java.util.List;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeDao {
    void addOvertime(Overtime overtime);

    List<Overtime> pageList(PageParam pageParam);
}
