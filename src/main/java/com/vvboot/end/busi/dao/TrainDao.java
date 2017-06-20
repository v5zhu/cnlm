package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Train;
import com.vvboot.end.busi.params.PageParam;

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
