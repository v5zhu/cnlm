package com.vvboot.end.dao;


import com.vvboot.end.entity.AuthMenu;
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
public interface AuthMenuMybatisDao {
    int insertAuthMenu(AuthMenu authMenu);

    AuthMenu findByAuthMenu(AuthMenu authMenu);

    int updateAuthMenu(Map params);

    int deleteAuthMenu(AuthMenu authMenu);

    List<AuthMenu> findAll();

    int deleteAuthMenuByMenuId(Long menuId);

    int deleteAuthMenuByAuthId(Long authId);

    int insertAuthMenuInBatch(Map params);

    List<AuthMenu> findAllAuthmenuByMenuId(Long menuId);
}
