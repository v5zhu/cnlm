package net.leebao.busi.service;


import com.alibaba.fastjson.JSONArray;
import net.leebao.busi.dto.ToutiaoDto;
import net.leebao.busi.params.PageParam;
import net.leebao.core.commons.Pageable;

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
