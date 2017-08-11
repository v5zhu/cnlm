package me.cnlm.busi.service;

import com.alibaba.fastjson.JSONArray;
import me.cnlm.busi.entity.SchoolOrganization;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;

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
