package net.leebao.busi.service;

import com.alibaba.fastjson.JSONArray;
import net.leebao.busi.entity.SchoolOrganization;
import net.leebao.core.commons.PageParam;
import net.leebao.core.commons.Pageable;

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
