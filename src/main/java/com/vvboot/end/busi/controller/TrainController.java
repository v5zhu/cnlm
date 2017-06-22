package com.vvboot.end.busi.controller;

import com.vvboot.end.busi.entity.Train;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.busi.service.TrainService;
import com.vvboot.end.core.commons.Pageable;
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
@RequestMapping(value = "/api/v1/train")
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity add(@RequestBody Train train) {
        try {
            trainService.addTrain(train);
            Success ok = new Success("添加成功", "恭喜你!添加成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "list", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pagelist(@RequestBody PageParam pageParam) {
        try {
            Pageable pageable = trainService.trainList(pageParam);
            Success ok = new Success(pageable, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
