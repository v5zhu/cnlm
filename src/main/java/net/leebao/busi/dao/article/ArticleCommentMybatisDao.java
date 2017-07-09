package net.leebao.busi.dao.article;


import net.leebao.busi.entity.article.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleCommentMybatisDao {
    int launchComment(ArticleComment comment);

    ArticleComment findById(String commentId);

    List<ArticleComment> commentList(String articleId);

    int findCommentNumbers(String articleId);

    List<String> findCommentSponsors(String articleId);

    int isFollower(String sponsorId);

    int increaseFollows(String commentId);

    void increaseApprovalAmount(String objectId);

    void increaseOpposeAmount(String articleId);

    int findApprovalAmountById(String objectId);

    int findOpposeAmountById(String objectId);
}
