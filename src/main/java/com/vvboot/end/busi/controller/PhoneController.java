package com.vvboot.end.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.busi.dto.PhoneCodeDto;
import com.vvboot.end.busi.service.PhoneService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.CoreException;
import com.vvboot.end.core.exception.InnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/phone")
public class PhoneController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "check", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity check(@RequestBody JSONObject phone) {
        try {
            phoneService.checkPhone(phone.getString("phone"));
            Success ok = new Success(null, "恭喜你，该号码可用");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            logger.info("异常:", e);
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "code", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity generateCode(@RequestParam("phone") String phone) {
        try {
            phoneService.generatePhoneCode(phone);
            Success ok = new Success( "操作成功", "生成验证码成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            logger.info("异常:", e);
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "verify", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity verify(@RequestBody PhoneCodeDto phoneCodeDto) {
        try {
            phoneService.verifyPhoneCode(phoneCodeDto.getPhone(), phoneCodeDto.getPresCode());
            Success ok = new Success(null, "恭喜你，验证成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            logger.info("异常:", e);
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
