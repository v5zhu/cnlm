package me.cnlm.busi.dao.init.article;


import me.cnlm.busi.entity.init.article.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleTypeMybatisDao {
    List<ArticleType> findTypes();
}
