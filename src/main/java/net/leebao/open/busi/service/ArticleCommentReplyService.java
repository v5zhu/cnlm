package net.leebao.open.busi.service;

import net.leebao.open.busi.dto.article.ArticleCommentReplyDto;

import java.util.List;

/**
 * Created by PAX on 2017/4/12.
 */
public interface ArticleCommentReplyService {
    ArticleCommentReplyDto addReply(ArticleCommentReplyDto replyDto);

    List<ArticleCommentReplyDto> replyList(String commentId);
}
