package com.vvboot.end.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.entity.Route;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/system/route")
public class RouteController {
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;



    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addRoute(@RequestBody Route route) {
        try {
            logger.info("接收到新增路由:[{}]", JSONObject.toJSONString(route));
            Route r = routeService.addRoute(route);
            Success ok = new Success(r, "新增成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateRoute(@RequestBody Route route) {
        try {
            logger.info("接收到修改路由:[{}]", JSONObject.toJSONString(route));
            Route r = routeService.updateRoute(route);
            Success ok = new Success(r, "修改成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findRoutesBySuperId(@RequestParam(value = "superId", defaultValue = "") Long superId) {
        try {
            logger.info("superId:[{}]下路由列表:[{}]", superId);
            List<Route> routes = routeService.findBySuperId(superId);
            Success ok = new Success(routes, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{routeId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findRouteByRouteId(@PathVariable("routeId") Long routeId) {
        try {
            logger.info("路由:[{}]详情", routeId);
            Route route = routeService.findByRouteId(routeId);
            Success ok = new Success(route, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{routeId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteByRouteId(@PathVariable("routeId") Long routeId) {
        try {
            logger.info("删除路由:[{}]", routeId);
            routeService.deleteRoute(routeId);
            Success ok = new Success("删除成功", "删除成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "pageable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pageRoutes(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            PageObject<Route> routePageObject = routeService.findAllRoutes(page, pageSize);
            Success ok = new Success(routePageObject, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity lock(@RequestParam("routeId") Long routeId) {
        try {
            routeService.lock(routeId);
            Success ok = new Success("锁定成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity unlock(@RequestParam("routeId") Long routeId) {
        try {
            routeService.unlock(routeId);
            Success ok = new Success("解锁成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
