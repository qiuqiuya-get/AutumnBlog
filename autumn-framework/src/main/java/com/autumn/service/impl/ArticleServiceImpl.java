package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddArticleDto;
import com.autumn.domain.entity.Article;
import com.autumn.domain.entity.ArticleTag;
import com.autumn.domain.entity.Category;
import com.autumn.domain.vo.ArticleDetailVo;
import com.autumn.domain.vo.ArticleListVo;
import com.autumn.domain.vo.HotArticleVo;
import com.autumn.domain.vo.PageVo;
import com.autumn.enums.AppHttpCodeEnum;
import com.autumn.mapper.ArticleMapper;
import com.autumn.service.ArticleService;
import com.autumn.service.ArticleTagService;
import com.autumn.service.CategoryService;
import com.autumn.utils.BeanCopyUtils;
import com.autumn.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.autumn.constants.SystemConstants.ARTICLE_MAXIMUM_NUMBER;
import static com.autumn.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

/**
 * @Author: qiuqiuya
 * @Date: 2023/5/25 23:00   
 */
@Component
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<Object> hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>()
                .eq("status", ARTICLE_STATUS_NORMAL)//必须是正式文章
                .orderByDesc("view_count");//按照浏览量进行排序
        //最多只查询10条
        Page<Article> page = new Page<>(1, ARTICLE_MAXIMUM_NUMBER);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        //bean拷贝
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(vs);
    }


    @Override
    public ResponseResult<Object> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        QueryWrapper<Article> QueryWrapper = new QueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        QueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, "category_id", categoryId);
        // 状态是正式发布的
        QueryWrapper.eq("status", SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        QueryWrapper.orderByDesc("id");
        QueryWrapper.orderByDesc("is_top");

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, QueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
        articles = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult<Object> updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    @Transactional
    public ResponseResult<Object> add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);


        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> articleList(int pageNum, int pageSize, String title, String summary) {
        //查询条件
        QueryWrapper<Article> QueryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(title)) {
            QueryWrapper.like("title", title);
        }
        if (!Objects.isNull(summary)) {
            QueryWrapper.like("summary", summary);
        }
        // 状态是正式发布的
        QueryWrapper.eq("status", SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        QueryWrapper.orderByDesc("is_top");

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, QueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
        articles = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> updateArticle(Article article) {
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> delete(Long id) {
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<Article>()
                .eq("id", id)
                .eq("del_flag", SystemConstants.DELETE_FLAG);
        boolean update = update(updateWrapper);
        return update ? ResponseResult.okResult() : ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}
