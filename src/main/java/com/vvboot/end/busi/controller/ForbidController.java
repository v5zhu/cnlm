package com.vvboot.end.busi.controller;

import com.vvboot.end.busi.entity.Forbid;
import com.vvboot.end.busi.service.ForbidService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.CoreException;
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
@RequestMapping(value = "/api/v1/forbid")
public class ForbidController {
    private static final Logger logger = LoggerFactory.getLogger(ForbidController.class);

    @Autowired
    private ForbidService forbidService;

    @RequestMapping(value = "save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity add(@RequestBody Forbid forbid) {
        try {
            forbidService.addForbid(forbid);
            Success ok = new Success( "添加成功", "恭喜你!添加成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
