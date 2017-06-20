package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Overtime;
import com.vvboot.end.busi.entity.SchoolOrganization;
import com.vvboot.end.busi.params.PageParam;

import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface SchoolOrganizationDao {
    void addOrganization(SchoolOrganization organization);

    List<SchoolOrganization> dropdown();

    List<SchoolOrganization> pageList(PageParam pageParam);
}
