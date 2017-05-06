package com.vvboot.end.busi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.busi.dao.ToutiaoMybatisDao;
import com.vvboot.end.busi.dto.ToutiaoDto;
import com.vvboot.end.busi.entity.Toutiao;
import com.vvboot.end.busi.service.ToutiaoService;
import com.vvboot.end.core.commons.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.BeanMapper;

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
    public Pageable<ToutiaoDto> listToutiao(int page, int pageSize) {
        logger.info("拉取更新头条page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Toutiao> toutiaos = toutiaoMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Toutiao> pageInfo = new PageInfo<Toutiao>(toutiaos);

        List<ToutiaoDto> toutiaoDtos = BeanMapper.mapList(pageInfo.getList(), ToutiaoDto.class);
        Pageable<ToutiaoDto> pageObject = BeanMapper.map(pageInfo, Pageable.class);
        pageObject.setList(toutiaoDtos);

        return pageObject;
    }
}
