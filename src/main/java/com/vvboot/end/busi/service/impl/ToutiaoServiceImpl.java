package com.vvboot.end.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.busi.dao.ToutiaoMybatisDao;
import com.vvboot.end.busi.dto.ToutiaoDto;
import com.vvboot.end.busi.entity.Toutiao;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.busi.service.ToutiaoService;
import com.vvboot.end.core.commons.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.BeanMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LONG on 2017/3/22.
 */
@SuppressWarnings("ALL")
@Service
public class ToutiaoServiceImpl implements ToutiaoService {
    private static final Logger logger = LoggerFactory.getLogger(ToutiaoServiceImpl.class);

    @Autowired
    private ToutiaoMybatisDao toutiaoMybatisDao;

    @Override
    public Pageable<ToutiaoDto> listToutiao(PageParam pageParam) {
        logger.info("拉取更新头条:[{}]", JSONObject.toJSONString(pageParam));
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);//查询出总数

        List<Toutiao> toutiaos = toutiaoMybatisDao.findAll(pageParam);
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Toutiao> pageInfo = new PageInfo<Toutiao>(toutiaos);

        List<ToutiaoDto> toutiaoDtos = BeanMapper.mapList(pageInfo.getList(), ToutiaoDto.class);
        Pageable<ToutiaoDto> pageObject = BeanMapper.map(pageInfo, Pageable.class);
        pageObject.setList(toutiaoDtos);

        return pageObject;
    }

    @Override
    public JSONArray authorList() {
        logger.info("获取头条作者列表");
        List<HashMap<String, Integer>> authors = toutiaoMybatisDao.findAuthors();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(authors));
        return array;
    }
}
