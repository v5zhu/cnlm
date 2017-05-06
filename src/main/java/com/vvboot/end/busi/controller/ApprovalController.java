package com.vvboot.end.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.busi.dto.common.ApprovalDto;
import com.vvboot.end.busi.service.ApprovalService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/approval")
public class ApprovalController {
    private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity approval(@RequestBody ApprovalDto approvalDto) {
        try {
            logger.info("接收到点赞:[{}]", JSONObject.toJSONString(approvalDto));
            int amount=approvalService.makeApproval(approvalDto);
            Success ok = new Success(amount, "点赞成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

}
