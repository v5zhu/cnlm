package com.vvboot.end.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.AuthMenu;
import com.vvboot.end.entity.Menu;
import com.vvboot.end.service.AuthMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LONG on 2017/4/18.
 */
@SuppressWarnings("ALL")
@Service
public class AuthMenuServiceImpl implements AuthMenuService {
    private static final Logger logger = LoggerFactory.getLogger(AuthMenuServiceImpl.class);
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
    public void assignAuthMenu(JSONObject authmenu) {
        Long menuId = authmenu.getLong("menuId");
        List<Long> authIds = Lists.newArrayList();
        JSONArray authList = authmenu.getJSONArray("authList");
        if (authList.size() > 0) {
            for (int i = 0; i < authList.size(); i++) {
                JSONObject obj = authList.getJSONObject(i);
                boolean checked = obj.getBoolean("checked");
                Long authId = obj.getLong("authId");
                if (checked) {
                    authIds.add(authId);
                }
            }
        }
        //先删除原来的配置
        int deleted = authMenuMybatisDao.deleteAuthMenuByMenuId(menuId);
        logger.info("删除原配置:[{}]",deleted);
        //然后插入新的配置
        Map params = new HashMap();
        params.put("menuId", menuId);
        params.put("authIds", authIds);
        int inserted = authMenuMybatisDao.insertAuthMenuInBatch(params);
        logger.info("新插入配置:[{}]",inserted);
    }

    @Override
    public JSONObject findAllAuthmenuByMenuId(Long menuId) {
        Menu menu = menuMybatisDao.findByMenuId(menuId);
        List<AuthMenu> authMenuList = authMenuMybatisDao.findAllAuthmenuByMenuId(menuId);
        JSONObject out = new JSONObject();
        out.put("menuId", menuId);
        out.put("menuName", menu.getName());
        List<JSONObject> list = Lists.newArrayList();
        if (authMenuList.size() > 0) {
            for (AuthMenu am : authMenuList) {
                JSONObject obj = new JSONObject();
                obj.put("authId", am.getAuthId());
                obj.put("authName", am.getAuthName());
                if (am.getMenuId() == null) {
                    obj.put("checked", false);
                } else {
                    obj.put("checked", true);
                }
                list.add(obj);
            }
        }
        out.put("authList", list);
        return out;
    }
}
