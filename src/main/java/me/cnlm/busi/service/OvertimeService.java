package me.cnlm.busi.service;

import me.cnlm.busi.entity.Overtime;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeService {
    void addOvertime(Overtime overtime);

    Pageable overtimeList(PageParam pageParam);
}
