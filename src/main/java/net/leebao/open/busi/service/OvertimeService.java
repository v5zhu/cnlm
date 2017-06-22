package net.leebao.open.busi.service;

import net.leebao.open.busi.entity.Overtime;
import net.leebao.open.busi.params.PageParam;
import net.leebao.open.core.commons.Pageable;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeService {
    void addOvertime(Overtime overtime);

    Pageable overtimeList(PageParam pageParam);
}
