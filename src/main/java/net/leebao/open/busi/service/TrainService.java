package net.leebao.open.busi.service;

import net.leebao.open.busi.entity.Train;
import net.leebao.open.busi.params.PageParam;
import net.leebao.open.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface TrainService {
    void addTrain(Train train);

    Pageable trainList(PageParam pageParam);
}
