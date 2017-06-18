package com.vvboot.end.busi.service;

import com.vvboot.end.busi.entity.Overtime;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.core.commons.Pageable;

/**
 * Created by LONG on 2017/6/18.
 */
public interface OvertimeService {
    void addOvertime(Overtime overtime);

    Pageable overtimeList(PageParam pageParam);
}
