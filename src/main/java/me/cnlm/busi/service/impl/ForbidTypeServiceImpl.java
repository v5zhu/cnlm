package me.cnlm.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import me.cnlm.busi.dao.ForbidTypeDao;
import me.cnlm.busi.service.ForbidTypeService;
import me.cnlm.busi.entity.ForbidType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LONG on 2017/5/27.
 */
@SuppressWarnings("ALL")
@Service
public class ForbidTypeServiceImpl implements ForbidTypeService {
    private static final Logger logger = LoggerFactory.getLogger(ForbidTypeServiceImpl.class);

    @Autowired
    private ForbidTypeDao forbidTypeDao;

    @Override
    public JSONArray dropdown() {
        List<ForbidType> list = forbidTypeDao.dropdown();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        logger.info("孕期禁忌类型下拉列表:{}", array.toJSONString());
        return array;
    }
}
