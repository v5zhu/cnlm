package com.vvboot.end.service;


import com.vvboot.end.commons.PageObject;
import com.vvboot.end.entity.Route;

import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
public interface RouteService {
    Route addRoute(Route route);

    Route findByRouteId(Long routeId);

    Route updateRoute(Route route);

    List<Route> findBySuperId(Long superId);

    void deleteRoute(Long routeId);

    PageObject<Route> findAllRoutes(int page, int pageSize);

    void lock(Long routeId);

    void unlock(Long routeId);
}
