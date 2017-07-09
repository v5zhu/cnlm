package net.leebao.busi.controller;

import com.alibaba.fastjson.JSONArray;
import net.leebao.core.exception.LeeBaoException;
import net.leebao.busi.service.ForbidTypeService;
import net.leebao.core.commons.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/forbid")
public class ForbidTypeController {
    private static final Logger logger = LoggerFactory.getLogger(ForbidTypeController.class);

    @Autowired
    private ForbidTypeService forbidTypeService;


    @RequestMapping(value = "type/dropdown", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity dropdown() {
        try {
            JSONArray array = forbidTypeService.dropdown();
            Success ok = new Success(array, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
