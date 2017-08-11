package me.cnlm.busi.service.impl;

import me.cnlm.busi.dao.PraiseDao;
import me.cnlm.busi.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuxl@paxsz.com on 2017/8/11.
 */
@SuppressWarnings("ALL")
@Service
public class PraiseServiceImpl implements PraiseService{

    @Autowired
    private PraiseDao praiseDao;

    @Override
    @Transactional
    public int increase() {
        praiseDao.increase();
        return totalPraise();
    }

    @Override
    public int totalPraise() {
        int total=praiseDao.findTotalPraise();
        return total;
    }
}
