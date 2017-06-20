package com.vvboot.end.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.busi.dao.SchoolOrganizationDao;
import com.vvboot.end.busi.entity.Overtime;
import com.vvboot.end.busi.entity.SchoolOrganization;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.busi.service.SchoolOrganizationService;
import com.vvboot.end.core.commons.Pageable;
import com.vvboot.end.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
@SuppressWarnings("ALL")
@Service
public class SchoolOrganizationServiceImpl implements SchoolOrganizationService {

    @Resource
    private SchoolOrganizationDao schoolOrganizationDao;

    @Override
    @Transactional
    public void addOrganization(SchoolOrganization organization) {
        organization.setCreateTime(DateUtils.nowTime());
        schoolOrganizationDao.addOrganization(organization);
    }

    @Override
    public JSONArray dropdown() {
        List<SchoolOrganization> list = schoolOrganizationDao.dropdown();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        return array;
    }

    @Override
    public Pageable orgList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
        List<SchoolOrganization> list = schoolOrganizationDao.pageList(pageParam);
        PageInfo pageInfo = new PageInfo(list);

        Pageable pageable = BeanMapper.map(pageInfo, Pageable.class);
        pageable.setList(list);

        return pageable;
    }
}
