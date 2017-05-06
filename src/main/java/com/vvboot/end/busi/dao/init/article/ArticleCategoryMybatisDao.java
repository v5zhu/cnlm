package com.vvboot.end.busi.dao.init.article;


import com.vvboot.end.busi.entity.init.article.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ArticleCategoryMybatisDao {

    List<ArticleCategory> findCategoriesByParentCategory(Map parentCategory);
}
