package com.vvboot.end.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.AuthRole;
import com.vvboot.end.entity.Role;
import com.vvboot.end.service.AuthRoleService;
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
public class AuthRoleServiceImpl implements AuthRoleService {
    private static final Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);
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
    public void assignAuthRole(JSONObject authrole) {
        Long roleId = authrole.getLong("roleId");
        List<Long> authIds = Lists.newArrayList();
        JSONArray authList = authrole.getJSONArray("authList");
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
        int deleted = authRoleMybatisDao.deleteAuthRoleByRoleId(roleId);
        logger.info("删除原配置:[{}]", deleted);
        //然后插入新的配置
        Map params = new HashMap();
        params.put("roleId", roleId);
        params.put("authIds", authIds);
        int inserted = authRoleMybatisDao.insertAuthRoleInBatch(params);
        logger.info("新插入配置:[{}]", inserted);
    }

    @Override
    public JSONObject findAllAuthroleByRoleId(Long roleId) {
        Role role = roleMybatisDao.findByRoleId(roleId);
        List<AuthRole> authRoleList = authRoleMybatisDao.findAllAuthroleByRoleId(roleId);
        JSONObject out = new JSONObject();
        out.put("roleId", roleId);
        out.put("roleName", role.getName());
        List<JSONObject> list = Lists.newArrayList();
        if (authRoleList.size() > 0) {
            for (AuthRole ar : authRoleList) {
                JSONObject obj = new JSONObject();
                obj.put("authId", ar.getAuthId());
                obj.put("authName", ar.getAuthName());
                if (ar.getRoleId() == null) {
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
