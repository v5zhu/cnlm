package net.leebao.open.busi.dao.article;


import net.leebao.open.busi.entity.article.ArticleCommentReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleCommentReplyMybatisDao {
    int addReply(ArticleCommentReply commentReply);

    ArticleCommentReply findById(String replyId);

    List<ArticleCommentReply> replyList(String commentId);

    List<String> findCommentReplySponsors(String articleId);

    List<String> findCommentRepliers(String articleId);

    void increaseApprovalAmount(String articleId);

    void increaseOpposeAmount(String articleId);

    int findApprovalAmountById(String objectId);

    int findOpposeAmountById(String objectId);
}
