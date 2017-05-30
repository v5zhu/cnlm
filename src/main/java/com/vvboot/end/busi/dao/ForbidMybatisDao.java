package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Forbid;
import com.vvboot.end.busi.params.PageParam;

import java.util.List;

/**
 * Created by LONG on 2017/5/26.
 */
public interface ForbidMybatisDao {
    int addForbid(Forbid forbid);

    Forbid detail(Long id);

    List<Forbid> pageList(PageParam pageParam);

    int update(Forbid forbid);

    int delete(Long id);
}
