package net.leebao.busi.dao;

import net.leebao.busi.params.PageParam;
import net.leebao.busi.entity.SchoolOrganization;

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
