package com.vvboot.end.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.AuthMenuService;
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
@RequestMapping(value = "/system/authmenu")
public class AuthMenuController {
    private static final Logger logger = LoggerFactory.getLogger(AuthMenuController.class);

    @Autowired
    private AuthMenuService authMenuService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity assignMenuAuth(@RequestBody JSONObject authMenu) {
        try {
            logger.info("接收到菜单权限配置:[{}]", authMenu.toJSONString());
            authMenuService.assignAuthMenu(authMenu);
            Success ok = new Success("配置成功", "配置菜单权限成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "list/{menuId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findAllAuthmenuByMenuId(@PathVariable("menuId")Long menuId) {
        try {
            JSONObject authMenu = authMenuService.findAllAuthmenuByMenuId(menuId);
            Success ok = new Success(authMenu, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e.getError(), HttpStatus.BAD_REQUEST);
        }
    }


}
