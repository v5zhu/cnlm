package me.cnlm.busi.controller;

import com.alibaba.fastjson.JSONObject;
import me.cnlm.busi.service.OpposeService;
import me.cnlm.core.exception.LeeBaoException;
import me.cnlm.busi.dto.common.OpposeDto;
import me.cnlm.core.commons.Success;
import me.cnlm.core.exception.InnerException;
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
@RequestMapping(value = "/api/v1/oppose")
public class OpposeController {
    private static final Logger logger = LoggerFactory.getLogger(OpposeController.class);

    @Autowired
    private OpposeService opposeService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity oppose(@RequestBody OpposeDto opposeDto) {
        try {
            logger.info("接收到反对:[{}]", JSONObject.toJSONString(opposeDto));
            int amount = opposeService.makeOppose(opposeDto);
            Success ok = new Success(amount, "反对成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
