package me.cnlm.busi.service;

import me.cnlm.busi.dto.article.ArticleCommentReplyDto;

import java.util.List;

/**
 * Created by PAX on 2017/4/12.
 */
public interface ArticleCommentReplyService {
    ArticleCommentReplyDto addReply(ArticleCommentReplyDto replyDto);

    List<ArticleCommentReplyDto> replyList(String commentId);
}
