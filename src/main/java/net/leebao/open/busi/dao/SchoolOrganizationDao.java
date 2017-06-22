package net.leebao.open.busi.dao;

import net.leebao.open.busi.params.PageParam;
import net.leebao.open.busi.entity.SchoolOrganization;

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
