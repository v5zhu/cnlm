package net.leebao.busi.dao;

import net.leebao.busi.entity.Train;
import net.leebao.busi.params.PageParam;

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
