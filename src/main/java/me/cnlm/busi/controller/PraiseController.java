package me.cnlm.busi.controller;

import com.alibaba.fastjson.JSONObject;
import me.cnlm.busi.entity.Praise;
import me.cnlm.busi.service.PraiseService;
import me.cnlm.core.commons.Success;
import me.cnlm.core.exception.InnerException;
import me.cnlm.core.exception.LeeBaoException;
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
@RequestMapping(value = "/api/v1/praise")
public class PraiseController {
    private static final Logger logger = LoggerFactory.getLogger(PraiseController.class);

    @Autowired
    private PraiseService praiseService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Praise praise) {
        try {
            logger.info("接收到点赞:[{}]", JSONObject.toJSONString(praise));
            int amount=praiseService.insert(praise);
            Success ok = new Success(amount, "点赞成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity totalPraise() {
        try {
            int amount=praiseService.totalPraise();
            Success ok = new Success(amount, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

}
