package net.leebao.open.busi.service;

import net.leebao.open.busi.dto.article.ArticleDto;
import net.leebao.open.busi.entity.init.article.ArticleCategory;
import net.leebao.open.busi.entity.init.article.ArticleType;
import net.leebao.open.core.commons.Pageable;

import java.util.List;

/**
 * Created by PAX on 2017/4/7.
 */
public interface ArticleService {
    /**
     * 用户撰写文章
     *
     * @param articleDto
     * @return
     */
    ArticleDto writeArticle(ArticleDto articleDto);

    /**
     * 查看文章详细信息
     *
     * @param id
     * @return
     */
    ArticleDto articleDetail(String id);

    /**
     * 分页查询文章列表
     *
     * @param uid      用户唯一标识,若为空则获取所有的文章列表
     * @param page     页码
     * @param pageSize 每页显示条数
     * @return
     */
    Pageable<ArticleDto> articleList(String uid, int page, int pageSize);

    /**
     * 获取文章类型列表
     *
     * @return
     */
    List<ArticleType> typeList();


    /**
     * 文章分类
     *
     * @param parentCategory
     * @return
     */
    List<ArticleCategory> findCategoriesByParentCategory(String parentCategory);
}
