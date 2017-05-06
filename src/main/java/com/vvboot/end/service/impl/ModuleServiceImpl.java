package com.vvboot.end.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.commons.ModuleSelectList;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.dao.*;
import com.vvboot.end.entity.Menu;
import com.vvboot.end.entity.Module;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.ModuleService;
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
public class ModuleServiceImpl implements ModuleService {
    private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);
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
    public Module addModule(Module module) {
        String error = ValidatorUtils.validate(validator, module);
        if (error != null) {
            throw new CoreException(error);
        }
        Date time = new Date();
        module.setCreateTime(time);
        module.setUpdateTime(time);
        int insert = moduleMybatisDao.insertModule(module);
        if (insert == 0) {
            throw new CoreException("插入模块失败");
        }
        //设置其他module sort
        Map params = new HashMap();
        params.put("moduleId", module.getModuleId());
        params.put("sort", module.getSort());
        int updated = moduleMybatisDao.moveDownExceptThis(params);
        return module;
    }

    @Override
    @Transactional
    public Module updateModule(Module module) {
        Date time = new Date();
        module.setUpdateTime(time);
        int updated = moduleMybatisDao.updateModule(module);
        if (updated == 0) {
            throw new CoreException("更新模块失败");
        }
        return module;
    }

    @Override
    @Transactional
    public List<Module> findCommonModules(Long roleId) {
        List<Module> modules = moduleMybatisDao.findCommonModules(roleId);
        return modules;
    }


    @Override
    @Transactional
    public List<Module> findModulesByLoginUser(String token) {
        List<Module> modules = moduleMybatisDao.findModulesByLoginUserToken(token);
        return modules;
    }

    @Override
    public List<Module> moduleList() {
        return moduleMybatisDao.findAll();
    }

    @Override
    public Module findByModuleId(Long moduleId) {
        return moduleMybatisDao.findByModuleId(moduleId);
    }

    @Override
    @Transactional
    public void deleteModule(Long moduleId) {
        Module module = moduleMybatisDao.findByModuleId(moduleId);
        if (module == null) {
            throw new CoreException("资源不存在");
        }
        if (module.getLocked() == 1) {
            throw new CoreException("资源已被锁定");
        }
        //是否还有关联的menu
        int count = menuMybatisDao.findCountByModuleId(moduleId);
        if (count > 0) {
            throw new CoreException("模块下菜单不为空");
        }
        int deleted = moduleMybatisDao.deleteModule(moduleId);
        if (deleted == 0) {
            throw new CoreException("删除失败");
        }
        Map params = new HashMap();
        params.put("moduleId", module.getModuleId());
        params.put("sort", module.getSort());
        int updated = moduleMybatisDao.moveUpExceptThis(params);
    }

    @Override
    public PageObject<Module> findAllModules(int page, int pageSize) {
        logger.info("获取所有模块page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Module> modules = moduleMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Module> pageInfo = new PageInfo<Module>(modules);

        PageObject<Module> pageObject = BeanMapper.map(pageInfo, PageObject.class);
        pageObject.setList(modules);
        return pageObject;
    }

    @Override
    public List<ModuleSelectList> findList() {
        List<Module> modules = moduleMybatisDao.findModuleSelectList();
        return BeanMapper.mapList(modules, ModuleSelectList.class);
    }

    @Override
    public PageObject<Module> findAllWithMenus(int page, int pageSize) {
        logger.info("获取所有模块包括菜单page:[{}],pageSize:[{}]", page, pageSize);
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Module> modules = moduleMybatisDao.findAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<Module> pageInfo = new PageInfo<Module>(modules);

        PageObject<Module> pageObject = BeanMapper.map(pageInfo, PageObject.class);
        if (modules.size() > 0) {
            for (int i = 0; i < modules.size(); i++) {
                Module module = modules.get(i);
                PageHelper.startPage(page, pageSize, true);
                List<Menu> menus = menuMybatisDao.findByModuleId(module.getModuleId());
                PageInfo<Menu> menuPageInfo = new PageInfo<Menu>(menus);

                PageObject<Menu> menuPageObject = BeanMapper.map(menuPageInfo, PageObject.class);
                menuPageObject.setList(menus);
                modules.get(i).setMenuPageObj(menuPageObject);
            }
        }
        pageObject.setList(modules);
        return pageObject;
    }

    @Override
    public List<Module> findModuleListWithMenus() {
        logger.info("获取所有模块包括菜单");

        List<Module> modules = moduleMybatisDao.findAllWithMenus();
        return modules;
    }

    @Override
    @Transactional
    public void moveTop(Long moduleId) {
        //判定当前模块是否为置顶，若是则取消操作
        Module module = moduleMybatisDao.findByModuleId(moduleId);
        if (module.getSort() == 1) {
            throw new CoreException("已置顶");
        }
        //当前模块sort设置为1,小于当前sort++
        int updated = moduleMybatisDao.moveTop(moduleId);
    }

    @Override
    @Transactional
    public void moveUp(Long moduleId) {
        //判定当前模块是否为置顶，若是则取消操作
        Module module = moduleMybatisDao.findByModuleId(moduleId);
        if (module.getSort() == 1) {
            throw new CoreException("已置顶");
        }
        int updated = moduleMybatisDao.moveUp(moduleId);
    }

    @Override
    @Transactional
    public void moveDown(Long moduleId) {
//判定当前模块是否为置顶，若是则取消操作
        Module module = moduleMybatisDao.findByModuleId(moduleId);
        int maxSort = moduleMybatisDao.findMaxSort();
        if (maxSort == module.getSort()) {
            throw new CoreException("已置底");
        }
        int updated = moduleMybatisDao.moveDown(moduleId);
    }

    @Override
    @Transactional
    public void lock(Long moduleId) {
        int locked = moduleMybatisDao.lock(moduleId);
        if (locked == 0) {
            throw new CoreException("锁定失败");
        }
    }

    @Override
    @Transactional
    public void unlock(Long moduleId) {
        int locked = moduleMybatisDao.unlock(moduleId);
        if (locked == 0) {
            throw new CoreException("解锁失败");
        }
    }
}
