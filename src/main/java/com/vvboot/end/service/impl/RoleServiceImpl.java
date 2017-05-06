package com.vvboot.end.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.Role;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.RoleService;
import com.vvboot.end.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
@SuppressWarnings("ALL")
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
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
    public Role addRole(Role role) {
        String error = ValidatorUtils.validate(validator, role);
        if (error != null) {
            throw new CoreException(error);
        }
        Date time = new Date();
        role.setCreateTime(time);
        role.setUpdateTime(time);
        int insertedRole = roleMybatisDao.insertRole(role);
        if (insertedRole == 1) {
            return role;
        } else {
            throw new CoreException("插入角色失败");
        }
    }

    @Override
    @Transactional
    public Role updateRole(Role role) {
        Date time = new Date();
        role.setUpdateTime(time);
        int updated = roleMybatisDao.updateRole(role);
        if (updated == 0) {
            throw new CoreException("更新角色失败");
        }
        return role;
    }

    @Override
    public List<Role> roleList() {
        return roleMybatisDao.findAll();
    }

    @Override
    public Role findByRoleId(Long roleId) {
        return roleMybatisDao.findByRoleId(roleId);
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        Role role=roleMybatisDao.findByRoleId(roleId);
        if(role==null){
            throw new CoreException("资源不存在");
        }
        if(role.getLocked()==1){
            throw new CoreException("资源已被锁定");
        }
        int deleted = roleMybatisDao.deleteRole(roleId);
        if (deleted == 0) {
            throw new CoreException("删除角色失败");
        }
        //删除authrole
        int deleted2=authRoleMybatisDao.deleteAuthRoleByRoleId(roleId);
        //删除userrole
        int deleted3=userRoleMybatisDao.deleteUserRoleByRoleId(roleId);
    }

    @Override
    public PageObject<Role> findAllRoles(int page, int pageSize) {
        logger.info("获取所有角色page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Role> roles = roleMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);

        PageObject<Role> pageObject = BeanMapper.map(pageInfo, PageObject.class);
        pageObject.setList(roles);
        return pageObject;
    }

    @Override
    @Transactional
    public void lock(Long roleId) {
        int locked = roleMybatisDao.lock(roleId);
        if (locked == 0) {
            throw new CoreException("锁定失败");
        }
    }

    @Override
    @Transactional
    public void unlock(Long roleId) {
        int locked = roleMybatisDao.unlock(roleId);
        if (locked == 0) {
            throw new CoreException("解锁失败");
        }
    }
}
