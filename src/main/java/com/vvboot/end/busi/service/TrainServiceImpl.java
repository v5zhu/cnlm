package com.vvboot.end.busi.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.busi.dao.StudentDao;
import com.vvboot.end.busi.dao.TrainDao;
import com.vvboot.end.busi.entity.Student;
import com.vvboot.end.busi.entity.Train;
import com.vvboot.end.busi.params.PageParam;
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
public class TrainServiceImpl implements TrainService {

    @Resource
    private TrainDao trainDao;

    @Override
    @Transactional
    public void addTrain(Train train) {
        train.setCreateTime(DateUtils.nowTime());
        trainDao.addTrain(train);
    }

    @Override
    public Pageable trainList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
        List<Train> list = trainDao.pageList(pageParam);
        PageInfo pageInfo = new PageInfo(list);

        Pageable pageable = BeanMapper.map(pageInfo, Pageable.class);
        pageable.setList(list);

        return pageable;
    }
}
