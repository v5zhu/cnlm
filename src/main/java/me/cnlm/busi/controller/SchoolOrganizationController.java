package me.cnlm.busi.controller;

import com.alibaba.fastjson.JSONArray;
import me.cnlm.core.commons.PageParam;
import me.cnlm.busi.entity.SchoolOrganization;
import me.cnlm.busi.service.SchoolOrganizationService;
import me.cnlm.core.commons.Pageable;
import me.cnlm.core.exception.LeeBaoException;
import me.cnlm.core.commons.Success;
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
@RequestMapping(value = "/api/v1/school/organization")
public class SchoolOrganizationController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolOrganizationController.class);

    @Autowired
    private SchoolOrganizationService schoolOrganizationService;

    @RequestMapping(value = "save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity add(@RequestBody SchoolOrganization organization) {
        try {
            schoolOrganizationService.addOrganization(organization);
            Success ok = new Success("添加成功", "恭喜你!添加成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "dropdown", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity dropdown() {
        try {
            JSONArray array = schoolOrganizationService.dropdown();
            Success ok = new Success(array, "查询成功");
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
            Pageable pageable = schoolOrganizationService.orgList(pageParam);
            Success ok = new Success(pageable, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
