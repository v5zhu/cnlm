package net.leebao.busi.service.impl;

import com.google.common.collect.Lists;
import net.leebao.busi.dao.article.ArticleCommentMybatisDao;
import net.leebao.busi.dto.article.ArticleCommentDto;
import net.leebao.busi.dto.article.ArticleCommentReplyDto;
import net.leebao.busi.entity.article.ArticleComment;
import net.leebao.core.exception.InnerException;
import net.leebao.busi.dao.article.ArticleCommentReplyMybatisDao;
import net.leebao.busi.dao.article.ArticleMybatisDao;
import net.leebao.busi.service.ArticleCommentService;
import net.leebao.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xuan.touch6@qq.com on 2017/4/12.
 */
@SuppressWarnings("ALL")
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentServiceImpl.class);
    @Autowired
    private ArticleCommentMybatisDao articleCommentMybatisDao;
    @Autowired
    private ArticleMybatisDao articleMybatisDao;
    @Autowired
    private ArticleCommentReplyMybatisDao articleCommentReplyMybatisDao;

    @Override
    @Transactional
    public ArticleCommentDto launchComment(ArticleCommentDto commentDto) {
        ArticleComment comment = BeanMapper.map(commentDto, ArticleComment.class);
        //评论增加一条，文章评论数递增
        String articleId = comment.getArticleId();
        int result = articleMybatisDao.increaseCommentAmount(articleId);
        if (result != 1) {
            throw new InnerException("内部错误");
        }
        logger.info("文章:[{}]评论数递增:[{}]", articleId, result);
        //为评论生成唯一id
        String commentId = StringUtils.generate32uuid();
        comment.setId(commentId);
        //判定该评论是第几楼
        int commentNums = articleCommentMybatisDao.findCommentNumbers(articleId);
        comment.setFloor(commentNums + 1);
        //判定评论以及回复共参与的人数
        List<String> commentSponsors = articleCommentMybatisDao.findCommentSponsors(articleId);
        List<String> replySponsors = articleCommentReplyMybatisDao.findCommentReplySponsors(articleId);
        List<String> repliers = articleCommentReplyMybatisDao.findCommentRepliers(articleId);
        Set<String> follows = new HashSet<>();
        follows.addAll(commentSponsors);
        follows.addAll(replySponsors);
        follows.addAll(repliers);
        if (follows.contains(comment.getSponsorId())) {
            comment.setFollows(follows.size());
        } else {
            comment.setFollows(follows.size() + 1);
        }
        //创建时间、更新时间
        Date time = new Date();
        comment.setCreateTime(time);
        comment.setUpdateTime(time);
        int inserted = articleCommentMybatisDao.launchComment(comment);
        if (inserted != 1) {
            throw new InnerException("插入评论出错");
        }
        logger.info("文章:[{}]新增评论结果:[{}]", comment.getArticleId(), inserted);
        return BeanMapper.map(comment, ArticleCommentDto.class);
    }

    @Override
    public List<ArticleCommentDto> commentList(String articleId) {
        List<ArticleComment> comments = articleCommentMybatisDao.commentList(articleId);
        if (comments.size() > 0) {
            List<ArticleCommentDto> commentDtos = BeanMapper.mapList(comments, ArticleCommentDto.class);
            int total = commentDtos.size();
            for (int i = 0; i < total; i++) {
                List<ArticleCommentReplyDto> replyDtos = BeanMapper.mapList(comments.get(i).getArticleCommentReplyList(), ArticleCommentReplyDto.class);
                commentDtos.get(i).setArticleCommentReplyDtoList(replyDtos);
            }
            return commentDtos;
        }
        return Lists.newArrayList();
    }
}
