package com.vvboot.end.busi.service.impl;

import com.vvboot.end.busi.dao.ToutiaoMybatisDao;
import com.vvboot.end.busi.dao.article.ArticleCommentMybatisDao;
import com.vvboot.end.busi.dao.article.ArticleCommentReplyMybatisDao;
import com.vvboot.end.busi.dao.article.ArticleMybatisDao;
import com.vvboot.end.busi.dao.common.ApprovalMybatisDao;
import com.vvboot.end.busi.dao.common.OpposeMybatisDao;
import com.vvboot.end.busi.dto.common.ApprovalDto;
import com.vvboot.end.busi.entity.common.Approval;
import com.vvboot.end.busi.service.ApprovalService;
import com.vvboot.end.core.exception.CoreException;
import com.vvboot.end.utils.ValidatorUtils;
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
            throw new CoreException(error);
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
            throw new CoreException("重复操作");
        }
    }
}
