package com.vvboot.end.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.AuthRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/system/authrole")
public class AuthRoleController {
    private static final Logger logger = LoggerFactory.getLogger(AuthRoleController.class);

    @Autowired
    private AuthRoleService authRoleService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity assignMenuAuth(@RequestBody JSONObject authrole) {
        try {
            logger.info("接收到角色权限配置:[{}]", authrole.toJSONString());
            authRoleService.assignAuthRole(authrole);
            Success ok = new Success( "配置成功", "配置角色权限成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "list/{roleId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findAllAuthroleByRoleId(@PathVariable("roleId") Long roleId) {
        try {
            JSONObject authrole = authRoleService.findAllAuthroleByRoleId(roleId);
            Success ok = new Success(authrole, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e.getError(), HttpStatus.BAD_REQUEST);
        }
    }
}
