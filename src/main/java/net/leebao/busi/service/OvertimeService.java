package net.leebao.busi.service;

import net.leebao.busi.entity.Overtime;
import net.leebao.core.commons.PageParam;
import net.leebao.core.commons.Pageable;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeService {
    void addOvertime(Overtime overtime);

    Pageable overtimeList(PageParam pageParam);
}
