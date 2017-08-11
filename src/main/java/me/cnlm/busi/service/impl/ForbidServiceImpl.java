package me.cnlm.busi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cnlm.core.commons.PageParam;
import me.cnlm.busi.entity.Forbid;
import me.cnlm.busi.service.ForbidService;
import me.cnlm.core.commons.Pageable;
import me.cnlm.busi.dao.ForbidMybatisDao;
import me.cnlm.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.List;

/**
 * Created by LONG on 2017/5/26.
 */
@SuppressWarnings("ALL")
@Service
public class ForbidServiceImpl implements ForbidService {
    private static final Logger logger = LoggerFactory.getLogger(ForbidServiceImpl.class);

    @Autowired
    private ForbidMybatisDao forbidMybatisDao;

    @Override
    public void addForbid(Forbid forbid) {
        forbid.setCreateTime(DateUtils.nowTime());
        forbid.setCreateUser("管理员");

        int inserted = forbidMybatisDao.addForbid(forbid);
        logger.info("添加禁忌结果:inserted:{}", inserted);
    }

    @Override
    public Forbid detail(Long id) {
        logger.info("查询禁忌详情:id=>[{}]", id);
        Forbid forbid = forbidMybatisDao.detail(id);
        logger.info("查询结果:{}", JSONObject.toJSONString(forbid));
        return forbid;
    }

    @Override
    public Pageable pageList(PageParam pageParam) {
        logger.info("分页查询禁忌列表:pageParam:{}", JSONObject.toJSONString(pageParam));
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize(), true);
        List<Forbid> list = forbidMybatisDao.pageList(pageParam);
        PageInfo pageInfo = new PageInfo(list);

        Pageable pageable = BeanMapper.map(pageInfo, Pageable.class);
        pageable.setList(list);

        logger.info("查询数量:{},总数:{}", list.size(), pageable.getTotal());
        return pageable;
    }

    @Override
    @Transactional
    public void update(Forbid forbid) {
        logger.info("修改禁忌:{}", JSONObject.toJSONString(forbid));
        forbid.setUpdateTime(DateUtils.nowTime());
        forbid.setUpdateUser("管理员");
        int updated = forbidMybatisDao.update(forbid);
        logger.info("修改禁忌结果:{}", updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("删除禁忌:{}", id);
        int deleted = forbidMybatisDao.delete(id);
        logger.info("删除禁忌结果:{}", deleted);
    }
}
