package net.leebao.busi.service;

import net.leebao.busi.entity.Forbid;
import net.leebao.core.commons.PageParam;
import net.leebao.core.commons.Pageable;

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
