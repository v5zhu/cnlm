package net.leebao.busi.service.impl;

import net.leebao.busi.dao.article.ArticleCommentMybatisDao;
import net.leebao.busi.dto.article.ArticleCommentReplyDto;
import net.leebao.busi.entity.article.ArticleCommentReply;
import net.leebao.busi.dao.article.ArticleMybatisDao;
import net.leebao.busi.service.ArticleCommentReplyService;
import net.leebao.busi.dao.article.ArticleCommentReplyMybatisDao;
import net.leebao.core.exception.InnerException;
import net.leebao.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by xuan.touch6@qq.com on 2017/4/12.
 */
@SuppressWarnings("ALL")
@Service
public class ArticleCommentReplyServiceImpl implements ArticleCommentReplyService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentReplyServiceImpl.class);

    @Autowired
    private ArticleCommentReplyMybatisDao articleCommentReplyMybatisDao;
    @Autowired
    private ArticleCommentMybatisDao articleCommentMybatisDao;
    @Autowired
    private ArticleMybatisDao articleMybatisDao;

    @Override
    @Transactional
    public ArticleCommentReplyDto addReply(ArticleCommentReplyDto replyDto) {
        ArticleCommentReply reply = BeanMapper.map(replyDto, ArticleCommentReply.class);
        //文章follows是否递增
        int isfollower = articleCommentMybatisDao.isFollower(reply.getSponsorId());
        if (isfollower == 0) {
            //文章follows递增
            int followsIncrease = articleCommentMybatisDao.increaseFollows(reply.getCommentId());
            if (followsIncrease != 1) {
                throw new InnerException("内部错误");
            }
            logger.info("文章follows递增:[{}]", followsIncrease);
        }
        //不递增
        //生成id
        reply.setId(StringUtils.generate32uuid());
        //设置时间
        Date time = new Date();
        reply.setCreateTime(time);
        reply.setUpdateTime(time);
        int inserted = articleCommentReplyMybatisDao.addReply(reply);
        if (inserted != 1) {
            throw new InnerException("内部错误");
        }
        logger.info("添加评论:[{}]回复成功", reply.getCommentId());
        return BeanMapper.map(reply, ArticleCommentReplyDto.class);
    }

    @Override
    public List<ArticleCommentReplyDto> replyList(String commentId) {
        List<ArticleCommentReply> replies = articleCommentReplyMybatisDao.replyList(commentId);
        return BeanMapper.mapList(replies, ArticleCommentReplyDto.class);
    }
}
