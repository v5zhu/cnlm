package com.vvboot.end.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.Menu;
import com.vvboot.end.entity.Module;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.MenuService;
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
public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
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
    public Menu addMenu(Long moduleId, Menu menu) {
        menu.setModuleId(moduleId);
        String error = ValidatorUtils.validate(validator, menu);
        if (error != null) {
            throw new CoreException(error);
        }

        Module module = moduleMybatisDao.findByModuleId(moduleId);
        if (module == null) {
            throw new CoreException("资源不存在");
        }
        Date time = new Date();
        menu.setCreateTime(time);
        menu.setUpdateTime(time);
        int insertedMenu = menuMybatisDao.insertMenu(menu);
        if (insertedMenu == 0) {
            throw new CoreException("插入菜单失败");
        }
        Map params = new HashMap();
        params.put("moduleId", module.getModuleId());
        params.put("sort", module.getSort());
        int updated = menuMybatisDao.moveUpExceptThis(params);
        return menu;
    }

    @Override
    @Transactional
    public Menu updateMenu(Menu menu) {
        Date time = new Date();
        menu.setUpdateTime(time);
        int updated = menuMybatisDao.updateMenu(menu);
        if (updated == 0) {
            throw new CoreException("更新菜单失败");
        }
        return menu;
    }

    @Override
    public List<Menu> menuList() {
        return menuMybatisDao.findAll();
    }

    @Override
    public Menu findByMenuId(Long menuId) {
        return menuMybatisDao.findByMenuId(menuId);
    }

    @Override
    public List<Menu> findMenusByModuleId(Long moduleId) {
        return menuMybatisDao.findByModuleId(moduleId);
    }

    @Override
    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuMybatisDao.findByMenuId(menuId);
        if (menu == null) {
            throw new CoreException("资源不存在");
        }
        if (menu.getLocked() == 1) {
            throw new CoreException("资源已被锁定");
        }
        int deleted = menuMybatisDao.deleteMenu(menuId);
        if (deleted == 0) {
            throw new CoreException("删除菜单失败");
        }
        int deleted2 = authMenuMybatisDao.deleteAuthMenuByMenuId(menuId);
        //模块内菜单sort大于当前的--
        Map params = new HashMap();
        params.put("menuId", menuId);
        params.put("sort", menu.getSort());
        params.put("moduleId", menu.getModuleId());
        int moved = menuMybatisDao.moveUpExceptThis(params);
    }

    @Override
    public PageObject<Menu> findAllMenus(int page, int pageSize) {
        logger.info("获取所有菜单page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Menu> menus = menuMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menus);

        PageObject<Menu> pageObject = BeanMapper.map(pageInfo, PageObject.class);
        pageObject.setList(menus);
        return pageObject;
    }

    @Override
    @Transactional
    public void moveTop(Long menuId) {
        //判定当前是否为置顶，若是则取消操作
        Menu menu = menuMybatisDao.findByMenuId(menuId);
        if (menu.getSort() == 1) {
            throw new CoreException("已置顶");
        }
        //当前sort设置为1,小于当前sort++
        Map params = new HashMap();
        params.put("menuId", menuId);
        params.put("moduleId", menu.getModuleId());
        int updated = menuMybatisDao.moveTop(params);
    }

    @Override
    @Transactional
    public void moveUp(Long menuId) {
        //判定当前是否为置顶，若是则取消操作
        Menu menu = menuMybatisDao.findByMenuId(menuId);
        if (menu.getSort() == 1) {
            throw new CoreException("已置顶");
        }
        Map params = new HashMap();
        params.put("menuId", menuId);
        params.put("moduleId", menu.getModuleId());
        int updated = menuMybatisDao.moveUp(params);
    }

    @Override
    @Transactional
    public void moveDown(Long menuId) {
        //判定当前是否为置顶，若是则取消操作
        Menu menu = menuMybatisDao.findByMenuId(menuId);
        int maxSort = menuMybatisDao.findMaxSort(menu.getModuleId());
        if (maxSort == menu.getSort()) {
            throw new CoreException("已置底");
        }
        Map params = new HashMap();
        params.put("menuId", menuId);
        params.put("moduleId", menu.getModuleId());
        int updated = menuMybatisDao.moveDown(params);
    }

    @Override
    @Transactional
    public void lock(Long menuId) {
        int locked = menuMybatisDao.lock(menuId);
        if (locked == 0) {
            throw new CoreException("锁定失败");
        }
    }

    @Override
    @Transactional
    public void unlock(Long menuId) {
        int locked = menuMybatisDao.unlock(menuId);
        if (locked == 0) {
            throw new CoreException("解锁失败");
        }
    }
}
