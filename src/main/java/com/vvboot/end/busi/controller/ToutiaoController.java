package com.vvboot.end.busi.controller;

import com.alibaba.fastjson.JSONArray;
import com.vvboot.end.busi.dto.ToutiaoDto;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.busi.service.ToutiaoService;
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
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/toutiao")
public class ToutiaoController {
    private static final Logger logger = LoggerFactory.getLogger(ToutiaoController.class);

    @Autowired
    private ToutiaoService toutiaoService;

    @RequestMapping(value = "overview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity pull(@RequestBody PageParam pageParam) {
        try {
            Pageable<ToutiaoDto> toutiaoDtos = toutiaoService.listToutiao(pageParam);
            Success ok = new Success(toutiaoDtos, "更新成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "authors", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity authorList() {
        try {
            JSONArray array = toutiaoService.authorList();
            Success ok = new Success(array, "更新成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
