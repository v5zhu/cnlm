package com.vvboot.end.busi.dao.article;


import com.vvboot.end.busi.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleMybatisDao {
    int writeArticle(Article article);

    Article findById(String id);

    int updateArticle(Article article);

    List<Article> articleList(String uid);

    List<Article> findAll();

    int increaseCommentAmount(String articleId);

    void increaseApprovalAmount(String articleId);

    void increaseOpposeAmount(String articleId);

    int findApprovalAmountById(String objectId);

    int findOpposeAmountById(String objectId);

}
