package com.vvboot.end.busi.service.impl;

import com.vvboot.end.busi.dao.ForbidMybatisDao;
import com.vvboot.end.busi.entity.Forbid;
import com.vvboot.end.busi.service.ForbidService;
import com.vvboot.end.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LONG on 2017/5/26.
 */
@SuppressWarnings("ALL")
@Service
public class ForbidServiceImpl implements ForbidService {
    private static final Logger logger= LoggerFactory.getLogger(ForbidServiceImpl.class);

    @Autowired
    private ForbidMybatisDao forbidMybatisDao;

    @Override
    public void addForbid(Forbid forbid) {
        forbid.setCreateTime(DateUtils.nowTime());
        forbid.setCreateUser("管理员");

        int inserted=forbidMybatisDao.addForbid(forbid);
        logger.info("添加禁忌结果:inserted:{}",inserted);
    }
}
