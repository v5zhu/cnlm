package com.vvboot.end.service;


import com.vvboot.end.commons.PageObject;
import com.vvboot.end.entity.Menu;

import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
public interface MenuService {
    Menu addMenu(Long moduleId, Menu menu);

    Menu updateMenu(Menu menu);

    List<Menu> menuList();

    Menu findByMenuId(Long menuId);

    List<Menu> findMenusByModuleId(Long moduleId);

    void deleteMenu(Long menuId);

    PageObject<Menu> findAllMenus(int page, int pageSize);

    void moveTop(Long menuId);

    void moveUp(Long menuId);

    void moveDown(Long menuId);

    void lock(Long menuId);

    void unlock(Long menuId);
}
