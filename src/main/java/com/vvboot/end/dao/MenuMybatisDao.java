package com.vvboot.end.dao;


import com.vvboot.end.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/*
 * ============================================================================		
 * = COPYRIGHT		
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION		
 *   This software is supplied under the terms of a license agreement or		
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied		
 *   or disclosed except in accordance with the terms in that agreement.		
 *      Copyright (C) 2017-? PAX Technology, Inc. All rights reserved.		
 * Description: // Detail description about the function of this module,		
 *             // interfaces with the other modules, and dependencies. 		
 * Revision History:		
 * Date	                 Author	                  Action
 * 2017/2/23  	         zhuxl@paxsz.com        Create/Add/Modify/Delete
 * ============================================================================		
 */
@Mapper
public interface MenuMybatisDao {
    int insertMenu(Menu menu);

    Menu findByMenuId(Long menuId);

    int updateMenu(Menu menu);

    List<Menu> findAll();

    List<Menu> findByModuleId(Long moduleId);

    int deleteMenu(Long menuId);

    int lock(Long menuId);

    int unlock(Long menuId);

    int findCountByModuleId(Long moduleId);

    int moveUp(Map params);

    int findMaxSort(Long moduleId);

    int moveDown(Map params);

    int moveDownExceptThis(Map params);

    int moveUpExceptThis(Map params);

    int moveTop(Map params);

    int findCountByMenuIds(List<Long> menuIds);
}
