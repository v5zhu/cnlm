package me.cnlm.busi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cnlm.core.commons.PageParam;
import me.cnlm.busi.dao.TrainDao;
import me.cnlm.busi.entity.Train;
import me.cnlm.core.commons.Pageable;
import me.cnlm.utils.DateUtils;
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
