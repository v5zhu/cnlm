package com.vvboot.end.dao;


import com.vvboot.end.entity.Module;
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
public interface ModuleMybatisDao {
    int insertModule(Module module);

    Module findByModuleId(Long moduleId);

    int updateModule(Module module);

    List<Module> findCommonModules(Long roleId);

    List<Module> findModulesByLoginUserToken(String token);

    List<Module> findAll();

    List<Module> findAllWithMenus();

    int deleteModule(Long moduleId);

    List<Module> findModuleSelectList();

    int moveTop(Long moduleId);

    int moveUp(Long moduleId);

    int findMaxSort();

    int moveDown(Long moduleId);

    int moveDownExceptThis(Map params);

    int moveUpExceptThis(Map params);

    int lock(Long moduleId);

    int unlock(Long moduleId);
}
