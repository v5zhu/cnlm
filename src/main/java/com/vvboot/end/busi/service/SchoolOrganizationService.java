package com.vvboot.end.busi.service;

import com.alibaba.fastjson.JSONArray;
import com.vvboot.end.busi.entity.SchoolOrganization;
import com.vvboot.end.busi.entity.Student;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface SchoolOrganizationService {
    void addOrganization(SchoolOrganization organization);

    JSONArray dropdown();

    Pageable orgList(PageParam pageParam);
}
