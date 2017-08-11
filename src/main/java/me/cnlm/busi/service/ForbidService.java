package me.cnlm.busi.service;

import me.cnlm.busi.entity.Forbid;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;

/**
 * Created by LONG on 2017/5/26.
 */
public interface ForbidService {
    void addForbid(Forbid forbid);

    Forbid detail(Long id);

    Pageable pageList(PageParam pageParam);

    void update(Forbid forbid);

    void delete(Long id);
}
