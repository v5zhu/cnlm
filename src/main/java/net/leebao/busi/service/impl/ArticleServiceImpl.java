package net.leebao.busi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.leebao.auth.dao.UserDao;
import net.leebao.auth.entity.User;
import net.leebao.busi.dao.init.article.ArticleCategoryMybatisDao;
import net.leebao.busi.dao.init.article.ArticleTagMybatisDao;
import net.leebao.busi.dao.init.article.ArticleTypeMybatisDao;
import net.leebao.busi.dto.article.ArticleDto;
import net.leebao.busi.entity.init.article.ArticleCategory;
import net.leebao.busi.entity.init.article.ArticleTag;
import net.leebao.core.commons.Pageable;
import net.leebao.core.exception.LeeBaoException;
import net.leebao.busi.dao.article.ArticleMybatisDao;
import net.leebao.busi.entity.article.Article;
import net.leebao.busi.entity.init.article.ArticleType;
import net.leebao.busi.service.ArticleService;
import net.leebao.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.*;

/**
 * Created by PAX on 2017/4/7.
 */
@SuppressWarnings("ALL")
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private ArticleTagMybatisDao articleTagMybatisDao;
    @Autowired
    private ArticleMybatisDao articleMybatisDao;
    @Autowired
    private ArticleTypeMybatisDao articleTypeMybatisDao;
    @Autowired
    private ArticleCategoryMybatisDao articleCategoryMybatisDao;

    @Override
    @Transactional
    public ArticleDto writeArticle(ArticleDto articleDto) {
        //校验dto属性
        User author = userDao.findByUserId(articleDto.getUserId());
        if (author == null) {
            throw new LeeBaoException("非法操作");
        }
        Article article = BeanMapper.map(articleDto, Article.class);
        //articleId为空新增，不为空修改
        if (StringUtils.isBlank(article.getId())) {
            article.setId(StringUtils.generate32uuid());
            //插入文章标签
            if (StringUtils.isNotBlank(article.getTag())) {
                String[] tags = StringUtils.split(article.getTag(), ",");
                List<ArticleTag> tagList = new ArrayList<>();
                for (String tag : tags) {
                    ArticleTag at = new ArticleTag();
                    at.setArticleId(article.getId());
                    at.setName(tag);
                    tagList.add(at);
                }
                int tagcount = articleTagMybatisDao.addArticleTag(tagList);
                logger.info("文章:[{}]插入[{}]个标签", article.getId() + "/" + article.getTitle(), tagcount);
            }
            Date date = new Date();
            article.setUserId(author.getId());
            article.setAuthor(author.getName());
            article.setCreateTime(date);
            article.setUpdateTime(date);
            article.setAuditStatus(0);
            int inserted = articleMybatisDao.writeArticle(article);
            logger.info("插入文章数:[{}]", inserted);
            return BeanMapper.map(article, ArticleDto.class);
        } else {
            //修改
            //查询出原文章
            Article old = articleMybatisDao.findById(article.getId());
            //删除原来标签
            int d = articleTagMybatisDao.deleteTagsByArticleId(article.getId());
            logger.info("修改文章，删除原来标签个数:[{}]", d);
            //插入文章标签
            if (StringUtils.isNotBlank(article.getTag())) {
                String[] tags = StringUtils.split(article.getTag(), ",");
                List<ArticleTag> tagList = new ArrayList<>();
                for (String tag : tags) {
                    ArticleTag at = new ArticleTag();
                    at.setArticleId(article.getId());
                    at.setName(tag);
                    tagList.add(at);
                }
                int tagcount = articleTagMybatisDao.addArticleTag(tagList);
                logger.info("修改文章:[{}]插入[{}]个标签", article.getId() + "/" + article.getTitle(), tagcount);
            }
            Date date = new Date();
            article.setUserId(old.getUserId());
            article.setAuthor(old.getAuthor());
            article.setCreateTime(old.getCreateTime());
            article.setUpdateTime(date);
            article.setAuditStatus(0);
            int updated = articleMybatisDao.updateArticle(article);
            logger.info("修改文章数:[{}]", updated);
            return BeanMapper.map(article, ArticleDto.class);
        }
    }

    @Override
    public ArticleDto articleDetail(String id) {
        Article article = articleMybatisDao.findById(id);
        if (article == null) {
            return null;
        }
        return BeanMapper.map(article, ArticleDto.class);
    }

    @Override
    public Pageable<ArticleDto> articleList(String uid, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);//查询出总数

        List<Article> articles;
        if (StringUtils.isNotBlank(uid)) {
            articles = articleMybatisDao.articleList(uid);
        } else {
            articles = articleMybatisDao.findAll();
        }
        PageInfo<Article> pageInfo = new PageInfo<Article>(articles);

        List<ArticleDto> articleDtos = BeanMapper.mapList(articles, ArticleDto.class);
        Pageable<ArticleDto> pageObject = BeanMapper.map(pageInfo, Pageable.class);
        pageObject.setList(articleDtos);
        return pageObject;
    }

    @Override
    public List<ArticleType> typeList() {
        List<ArticleType> articleTypes = articleTypeMybatisDao.findTypes();
        return articleTypes;
    }

    @Override
    public List<ArticleCategory> findCategoriesByParentCategory(String parentCategory) {
        Map param = new HashMap();
        if (StringUtils.isNotBlank(parentCategory)) {
            param.put("parentCategory", parentCategory);
        } else {
            param.put("parentCategory", "");
        }
        return articleCategoryMybatisDao.findCategoriesByParentCategory(param);
    }
}
