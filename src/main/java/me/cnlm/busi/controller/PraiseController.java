package me.cnlm.busi.controller;

import com.alibaba.fastjson.JSONObject;
import me.cnlm.busi.service.PraiseService;
import me.cnlm.core.commons.Success;
import me.cnlm.core.exception.InnerException;
import me.cnlm.core.exception.LeeBaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping(value = "/increase")
    @ResponseBody
    public void increase(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            int amount = praiseService.increase();
            Success ok = new Success(amount, "点赞成功");


            String result = JSONObject.toJSONString(ok);

            //前端传过来的回调函数名称
            String callback = request.getParameter("callback");
            //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
            result = callback + "(" + result + ")";

            response.getWriter().write(result);

        } catch (LeeBaoException e) {
            e.printStackTrace();
        } catch (InnerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/total",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void totalPraise(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            int amount = praiseService.totalPraise();
            Success ok = new Success(amount, "点赞成功");


            String result = JSONObject.toJSONString(ok);

            //前端传过来的回调函数名称
            String callback = request.getParameter("callback");
            //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
            result = callback + "(" + result + ")";

            response.getWriter().write(result);
        }  catch (LeeBaoException e) {
            e.printStackTrace();
        } catch (InnerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
