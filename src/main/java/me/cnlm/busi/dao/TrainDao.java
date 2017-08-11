package me.cnlm.busi.dao;

import me.cnlm.busi.entity.Train;
import me.cnlm.core.commons.PageParam;

import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface TrainDao {
    void addTrain(Train train);

    List<Train> pageList(PageParam pageParam);
}
