package com.vvboot.end.service;


import com.vvboot.end.commons.ModuleSelectList;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.entity.Module;

import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
public interface ModuleService {
    Module addModule(Module module);

    Module updateModule(Module module);

    List<Module> findCommonModules(Long roleId);

    List<Module> findModulesByLoginUser(String token);

    List<Module> moduleList();

    Module findByModuleId(Long moduleId);

    void deleteModule(Long moduleId);

    PageObject<Module> findAllModules(int page, int pageSize);

    List<ModuleSelectList> findList();

    PageObject<Module> findAllWithMenus(int page, int pageSize);

    List<Module> findModuleListWithMenus();

    void moveTop(Long moduleId);

    void moveUp(Long moduleId);

    void moveDown(Long moduleId);

    void lock(Long moduleId);

    void unlock(Long moduleId);
}
