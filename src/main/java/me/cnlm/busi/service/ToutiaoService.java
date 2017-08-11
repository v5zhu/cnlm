package me.cnlm.busi.service;


import com.alibaba.fastjson.JSONArray;
import me.cnlm.core.commons.PageParam;
import me.cnlm.busi.dto.ToutiaoDto;
import me.cnlm.core.commons.Pageable;

/**
 * Created by LONG on 2017/3/22.
 */
public interface ToutiaoService {
    /** 拉取头条更新
     * @return
     */
    Pageable<ToutiaoDto> listToutiao(PageParam pageParam);

    /**作者列表
     * @return
     */
    JSONArray authorList();
}
