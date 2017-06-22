package net.leebao.open.busi.service;

import net.leebao.open.busi.entity.Forbid;
import net.leebao.open.busi.params.PageParam;
import net.leebao.open.core.commons.Pageable;

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
