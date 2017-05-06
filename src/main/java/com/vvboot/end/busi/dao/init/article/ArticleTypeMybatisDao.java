package com.vvboot.end.busi.dao.init.article;


import com.vvboot.end.busi.entity.init.article.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleTypeMybatisDao {
    List<ArticleType> findTypes();
}
