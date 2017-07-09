package net.leebao.busi.service;

import net.leebao.busi.entity.Train;
import net.leebao.busi.params.PageParam;
import net.leebao.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface TrainService {
    void addTrain(Train train);

    Pageable trainList(PageParam pageParam);
}
