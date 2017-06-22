package com.vvboot.end.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.busi.dto.UserDto;
import com.vvboot.end.busi.params.LoginParam;
import com.vvboot.end.busi.params.PerfectInfoParam;
import com.vvboot.end.busi.params.RegisterParam;
import com.vvboot.end.busi.params.UniqueParam;
import com.vvboot.end.busi.service.UserService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.LeeBaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity register(@RequestBody RegisterParam registerParam) {
        try {
            logger.info("接收到注册信息:[{}]", JSONObject.toJSONString(registerParam));
            userService.register(registerParam);
            Success ok = new Success( "注册成功", "恭喜你!注册成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginParam loginParam) {
        try {
            UserDto userDto = userService.login(loginParam);
            Success ok = new Success( userDto, "恭喜你!登录成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "perfect", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity perfectInfo(@RequestBody PerfectInfoParam infoParam) {
        try {
            userService.perfectUserInfo(infoParam);
            Success ok = new Success( "更新成功", "恭喜你!资料更新成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "info", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity userInfo(@RequestBody UniqueParam uniqueParam) {
        try {
            UserDto userDto = userService.getUserInfo(uniqueParam.getUid());
            Success ok = new Success(userDto, "查询成功");
            return new ResponseEntity(userDto, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
