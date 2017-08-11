package me.cnlm.busi.service;

import me.cnlm.busi.entity.Praise;

/**
 * Created by zhuxl@paxsz.com on 2017/8/11.
 */
public interface PraiseService {
    int insert(Praise praise);

    int totalPraise();
}
