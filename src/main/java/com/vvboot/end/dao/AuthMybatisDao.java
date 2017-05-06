package com.vvboot.end.dao;

import com.vvboot.end.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
public interface AuthMybatisDao {
    int insertAuth(Auth auth);

    Auth findByAuthId(Long authId);

    int updateAuth(Auth auth);

    List<Auth> findAll();

    int deleteAuth(Long authId);

    int lock(Long authId);

    int unlock(Long authId);

    int findCountByAuthIds(List<Long> authIds);
}
