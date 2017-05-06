package com.vvboot.end.controller;

import com.vvboot.end.commons.PageObject;
import com.vvboot.end.commons.Success;
import com.vvboot.end.dto.UserDto;
import com.vvboot.end.exception.CoreException;
import com.vvboot.end.service.UserCenterService;
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
@RequestMapping(value = "/system/usercenter")
public class UserCenterController {
    private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserCenterService userCenterService;

    @RequestMapping(value = "pageable", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pageUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            PageObject<UserDto> pageObject = userCenterService.findAllUsers(page, pageSize);
            Success ok = new Success(pageObject, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
