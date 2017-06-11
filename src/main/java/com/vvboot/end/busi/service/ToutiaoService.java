package com.vvboot.end.busi.service;


import com.alibaba.fastjson.JSONArray;
import com.vvboot.end.busi.dto.ToutiaoDto;
import com.vvboot.end.core.commons.Pageable;

/**
 * Created by LONG on 2017/3/22.
 */
public interface ToutiaoService {
    /** 拉取头条更新
     * @return
     */
    Pageable<ToutiaoDto> listToutiao(int pageNo, int pageSize);

    /**作者列表
     * @return
     */
    JSONArray authorList();
}
