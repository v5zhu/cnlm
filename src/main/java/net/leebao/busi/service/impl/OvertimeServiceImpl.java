package net.leebao.busi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.leebao.busi.dao.OvertimeDao;
import net.leebao.busi.entity.Overtime;
import net.leebao.busi.params.PageParam;
import net.leebao.busi.service.OvertimeService;
import net.leebao.core.commons.Pageable;
import net.leebao.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.List;

/**
 * Created by LONG on 2017/6/18.
 */
@SuppressWarnings("ALL")
@Service
public class OvertimeServiceImpl implements OvertimeService {

    private static final Logger logger = LoggerFactory.getLogger(OvertimeServiceImpl.class);

    @Autowired
    private OvertimeDao overtimeDao;

    @Override
    @Transactional
    public void addOvertime(Overtime overtime) {
        logger.info("录入新加班信息:{}", JSONObject.toJSONString(overtime));
        overtime.setCreateTime(DateUtils.nowTime());
        overtimeDao.addOvertime(overtime);
    }

    @Override
    public Pageable overtimeList(PageParam pageParam) {
        logger.info("分页查询加班信息列表:pageParam:{}", JSONObject.toJSONString(pageParam));
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
        List<Overtime> list = overtimeDao.pageList(pageParam);
        PageInfo pageInfo = new PageInfo(list);

        Pageable pageable = BeanMapper.map(pageInfo, Pageable.class);
        pageable.setList(list);

        logger.info("查询数量:{},总数:{}", list.size(), pageable.getTotal());
        return pageable;
    }
}
