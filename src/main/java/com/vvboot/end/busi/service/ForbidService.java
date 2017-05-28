package com.vvboot.end.busi.service;

import com.vvboot.end.busi.entity.Forbid;
import com.vvboot.end.core.commons.Pageable;

/**
 * Created by LONG on 2017/5/26.
 */
public interface ForbidService {
    void addForbid(Forbid forbid);

    Forbid detail(Long id);

    Pageable pageList(int pageNo,int pageSize);

    void update(Forbid forbid);

    void delete(Long id);
}
