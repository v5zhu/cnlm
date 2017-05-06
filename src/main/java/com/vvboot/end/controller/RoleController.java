package com.vvboot.end.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.entity.Role;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.RoleService;
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
@RequestMapping(value = "/system/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addRole(@RequestBody Role role) {
        try {
            logger.info("接收到角色:[{}]", JSONObject.toJSONString(role));
            Role r = roleService.addRole(role);
            Success ok = new Success( r, "添加角色成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateRole(@RequestBody Role role) {
        try {
            logger.info("接收到角色修改:[{}]", JSONObject.toJSONString(role));
            Role r = roleService.updateRole(role);
            Success ok = new Success( r, "修改角色信息成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity roleList() {
        try {
            logger.info("获取角色列表");
            List<Role> roles = roleService.roleList();
            Success ok = new Success( roles, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findRoleById(@PathVariable("roleId") Long roleId) {
        try {
            logger.info("获取角色:[{}]列表", roleId);
            Role role = roleService.findByRoleId(roleId);
            Success ok = new Success( role, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteRole(@PathVariable("roleId") Long roleId) {
        try {
            logger.info("删除角色:[{}]", roleId);
            roleService.deleteRole(roleId);
            Success ok = new Success( "删除成功", "删除成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "pageable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pageRoles(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            PageObject<Role> rolePageObject = roleService.findAllRoles(page, pageSize);
            Success ok = new Success( rolePageObject, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity lock(@RequestParam("roleId") Long roleId) {
        try {
            roleService.lock(roleId);
            Success ok = new Success( "锁定成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity unlock(@RequestParam("roleId") Long roleId) {
        try {
            roleService.unlock(roleId);
            Success ok = new Success( "解锁成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
