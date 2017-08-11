package me.cnlm.busi.dao;

import me.cnlm.busi.entity.SchoolOrganization;
import me.cnlm.core.commons.PageParam;

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
