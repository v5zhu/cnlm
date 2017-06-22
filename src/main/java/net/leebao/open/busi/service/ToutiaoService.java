package net.leebao.open.busi.service;


import com.alibaba.fastjson.JSONArray;
import net.leebao.open.busi.dto.ToutiaoDto;
import net.leebao.open.busi.params.PageParam;
import net.leebao.open.core.commons.Pageable;

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
