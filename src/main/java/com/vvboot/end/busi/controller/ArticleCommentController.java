package com.vvboot.end.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.busi.dto.article.ArticleCommentDto;
import com.vvboot.end.busi.service.ArticleCommentService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.LeeBaoException;
import com.vvboot.end.core.exception.InnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/article/comment")
public class ArticleCommentController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

    @Autowired
    private ArticleCommentService articleCommentService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity launchNewComment(@RequestBody ArticleCommentDto commentDto) {
        try {
            logger.info("接收到新的文章评论:[{}]", JSONObject.toJSONString(commentDto));
            ArticleCommentDto comment = articleCommentService.launchComment(commentDto);
            Success ok = new Success(comment, "评论成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity commentList(@RequestParam("articleId") String articleId) {
        try {
            logger.info("获取文章:[{}]评论列表", articleId);
            List<ArticleCommentDto> commentDtos = articleCommentService.commentList(articleId);
            Success ok = new Success(commentDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

}
