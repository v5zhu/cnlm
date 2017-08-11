package me.cnlm.busi.service.impl;

import me.cnlm.busi.dao.ToutiaoMybatisDao;
import me.cnlm.busi.dao.article.ArticleCommentMybatisDao;
import me.cnlm.busi.dao.article.ArticleCommentReplyMybatisDao;
import me.cnlm.busi.dao.article.ArticleMybatisDao;
import me.cnlm.busi.dao.common.ApprovalMybatisDao;
import me.cnlm.busi.dao.common.OpposeMybatisDao;
import me.cnlm.busi.dto.common.ApprovalDto;
import me.cnlm.busi.entity.common.Approval;
import me.cnlm.busi.service.ApprovalService;
import me.cnlm.core.exception.LeeBaoException;
import me.cnlm.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.validation.Validator;
import java.util.Date;

/**
 * Created by xuan.touch6@qq.com on 2017/4/14.
 */
@SuppressWarnings("ALL")
@Service
public class ApprovalServiceImpl implements ApprovalService {
    private static final Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

    @Autowired
    private ApprovalMybatisDao approvalMybatisDao;
    @Autowired
    private OpposeMybatisDao opposeMybatisDao;
    @Autowired
    private ArticleMybatisDao articleMybatisDao;
    @Autowired
    private ArticleCommentMybatisDao articleCommentMybatisDao;
    @Autowired
    private ArticleCommentReplyMybatisDao articleCommentReplyMybatisDao;
    @Autowired
    private ToutiaoMybatisDao toutiaoMybatisDao;
    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public int makeApproval(ApprovalDto approvalDto) {
        String error = ValidatorUtils.validate(validator, approvalDto);
        if (error != null) {
            throw new LeeBaoException(error);
        }
        //点赞
        int approvalAmount = 0;
        Approval approval = BeanMapper.map(approvalDto, Approval.class);
        //插入点赞
        Date time = new Date();
        approval.setCreateTime(time);
        approval.setUpdateTime(time);
        int inserted = approvalMybatisDao.addApproval(approval);
        if (inserted == 1) {
            //插入返回1对象点赞数增加
            switch (approval.getTargetObject()) {
                case ARTICLE:
                    articleMybatisDao.increaseApprovalAmount(approval.getObjectId());
                    approvalAmount = articleMybatisDao.findApprovalAmountById(approval.getObjectId());
                    break;
                case ARTICLE_COMMENT:
                    articleCommentMybatisDao.increaseApprovalAmount(approval.getObjectId());
                    approvalAmount = articleCommentMybatisDao.findApprovalAmountById(approval.getObjectId());
                    break;
                case ARTICLE_COMMENT_REPLY:
                    articleCommentReplyMybatisDao.increaseApprovalAmount(approval.getObjectId());
                    approvalAmount = articleCommentReplyMybatisDao.findApprovalAmountById(approval.getObjectId());
                    break;
                case NEWS:
                    toutiaoMybatisDao.increaseApprovalAmount(approval.getObjectId());
                    approvalAmount = toutiaoMybatisDao.findApprovalAmountById(approval.getObjectId());
                    break;
            }
            return approvalAmount;
        } else {
            throw new LeeBaoException("重复操作");
        }
    }
}
