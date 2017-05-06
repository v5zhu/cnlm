package com.vvboot.end.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.commons.ModuleSelectList;
import com.vvboot.end.commons.PageObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.entity.Module;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.ModuleService;
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
@RequestMapping(value = "/system/module")
public class ModuleController {
    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addModule(@RequestBody Module module) {
        try {
            logger.info("接收到模块:[{}]", JSONObject.toJSONString(module));
            Module m = moduleService.addModule(module);
            Success ok = new Success(m, "添加模块成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/common/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findCommonModules(@RequestParam("roleId") long roleId) {
        try {
            logger.info("获取公共的模块roleId:[{}]", roleId);
            List<Module> moduleList = moduleService.findCommonModules(roleId);
            Success ok = new Success(moduleList, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/loginuser/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findModulesByLoginUser(@RequestParam("token") String token) {
        try {
            logger.info("获取登录用户拥有的模块token:[{}]", token);
            List<Module> moduleList = moduleService.findModulesByLoginUser(token);
            Success ok = new Success(moduleList, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateModule(@RequestBody Module module) {
        try {
            logger.info("接收到模块修改:[{}]", JSONObject.toJSONString(module));
            Module m = moduleService.updateModule(module);
            Success ok = new Success(m, "修改模块信息成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity moduleList() {
        try {
            logger.info("获取模块列表");
            List<Module> modules = moduleService.moduleList();
            Success ok = new Success(modules, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{moduleId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findModuleById(@PathVariable("moduleId") Long moduleId) {
        try {
            logger.info("获取模块:[{}]信息", moduleId);
            Module module = moduleService.findByModuleId(moduleId);
            Success ok = new Success(module, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{moduleId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteModule(@PathVariable("moduleId") Long moduleId) {
        try {
            logger.info("删除模块:[{}]", moduleId);
            moduleService.deleteModule(moduleId);
            Success ok = new Success("删除成功", "删除成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "pageable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pageModules(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            PageObject<Module> modulePageObject = moduleService.findAllModules(page, pageSize);
            Success ok = new Success(modulePageObject, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "pageable/withmenus", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pageModulesMenus(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            PageObject<Module> modulePageObject = moduleService.findAllWithMenus(page, pageSize);
            Success ok = new Success(modulePageObject, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "list/withmenus", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listAllWithMenus() {
        try {
            List<Module> list = moduleService.findModuleListWithMenus();
            Success ok = new Success(list, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/selectlist", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity moduleSelectList() {
        try {
            List<ModuleSelectList> list = moduleService.findList();
            Success ok = new Success(list, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity moveTop(@RequestParam("moduleId") Long moduleId) {
        try {
            moduleService.moveTop(moduleId);
            Success ok = new Success("置顶成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/up", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity moveUp(@RequestParam("moduleId") Long moduleId) {
        try {
            moduleService.moveUp(moduleId);
            Success ok = new Success("上移成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/down", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity moveDown(@RequestParam("moduleId") Long moduleId) {
        try {
            moduleService.moveDown(moduleId);
            Success ok = new Success("下移成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity lock(@RequestParam("moduleId") Long moduleId) {
        try {
            moduleService.lock(moduleId);
            Success ok = new Success("锁定成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity unlock(@RequestParam("moduleId") Long moduleId) {
        try {
            moduleService.unlock(moduleId);
            Success ok = new Success("解锁成功", "操作成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


}
