package com.vvboot.end.dao;


import com.vvboot.end.entity.Route;
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
public interface RouteMybatisDao {
    int insertRoute(Route route);

    Route findByRouteId(Long routeId);

    int updateRoute(Route route);

    List<Route> findBySuperId(Map params);

    int deleteRoute(Long routeId);

    int findSuperRankByRouteId(Long superId);

    List<Route> findAll();

    int lock(Long routeId);

    int unlock(Long routeId);
}
