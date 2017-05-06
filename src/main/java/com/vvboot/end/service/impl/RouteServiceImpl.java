package com.vvboot.end.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.Route;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.RouteService;
import com.vvboot.end.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.validation.Validator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LONG on 2017/4/18.
 */
@SuppressWarnings("ALL")
@Service
public class RouteServiceImpl implements RouteService {
    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);
    @Autowired
    private RoleMybatisDao roleMybatisDao;
    @Autowired
    private AuthMybatisDao authMybatisDao;
    @Autowired
    private ModuleMybatisDao moduleMybatisDao;
    @Autowired
    private MenuMybatisDao menuMybatisDao;
    @Autowired
    private UserRoleMybatisDao userRoleMybatisDao;
    @Autowired
    private AuthRoleMybatisDao authRoleMybatisDao;
    @Autowired
    private AuthMenuMybatisDao authMenuMybatisDao;
    @Autowired
    private UserMybatisDao userMybatisDao;
    @Autowired
    private RouteMybatisDao routeMybatisDao;
    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public Route addRoute(Route route) {
        String error = ValidatorUtils.validate(validator, route);
        if (error != null) {
            throw new CoreException(error);
        }
        Date time = new Date();
        if (route.getSuperId() == null) {
            route.setRank(1);
        } else {
            int superRank = routeMybatisDao.findSuperRankByRouteId(route.getSuperId());
            route.setRank(superRank + 1);
        }
        route.setCreateTime(time);
        route.setUpdateTime(time);
        int inserted = routeMybatisDao.insertRoute(route);
        if (inserted == 0) {
            throw new CoreException("插入路由失败");
        }
        return route;
    }

    @Override
    public Route findByRouteId(Long routeId) {
        return routeMybatisDao.findByRouteId(routeId);
    }

    @Override
    @Transactional
    public Route updateRoute(Route route) {
        String error = ValidatorUtils.validate(validator, route);
        if (error != null) {
            throw new CoreException(error);
        }
        Date time = new Date();
        if (route.getSuperId() == null) {
            route.setRank(1);
        } else {
            int superRank = routeMybatisDao.findSuperRankByRouteId(route.getSuperId());
            route.setRank(superRank + 1);
        }
        route.setUpdateTime(time);
        int updated = routeMybatisDao.updateRoute(route);
        if (updated == 0) {
            throw new CoreException("更新路由失败");
        }
        return route;
    }

    @Override
    public List<Route> findBySuperId(Long superId) {
        Map params = new HashMap();
        params.put("superId", superId);
        return routeMybatisDao.findBySuperId(params);
    }

    @Override
    @Transactional
    public void deleteRoute(Long routeId) {
        Route route = routeMybatisDao.findByRouteId(routeId);
        if (route == null) {
            throw new CoreException("资源不存在");
        }
        if (route.getLocked() == 1) {
            throw new CoreException("资源已被锁定");
        }
        int deleted = routeMybatisDao.deleteRoute(routeId);
        if (deleted == 0) {
            throw new CoreException("删除路由失败");
        }
    }

    @Override
    public PageObject<Route> findAllRoutes(int page, int pageSize) {
        logger.info("获取所有路由page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Route> routes = routeMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Route> pageInfo = new PageInfo<Route>(routes);

        PageObject<Route> pageObject = BeanMapper.map(pageInfo, PageObject.class);
        pageObject.setList(routes);
        return pageObject;
    }

    @Override
    @Transactional
    public void lock(Long routeId) {
        int locked = routeMybatisDao.lock(routeId);
        if (locked == 0) {
            throw new CoreException("锁定失败");
        }
    }

    @Override
    @Transactional
    public void unlock(Long routeId) {
        int locked = routeMybatisDao.unlock(routeId);
        if (locked == 0) {
            throw new CoreException("解锁失败");
        }
    }
}
