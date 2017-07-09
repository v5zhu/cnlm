package net.leebao.busi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.leebao.busi.dao.TrainDao;
import net.leebao.busi.entity.Train;
import net.leebao.busi.params.PageParam;
import net.leebao.core.commons.Pageable;
import net.leebao.utils.DateUtils;
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
