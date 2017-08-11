package me.cnlm.busi.service;

import me.cnlm.busi.entity.Train;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface TrainService {
    void addTrain(Train train);

    Pageable trainList(PageParam pageParam);
}
