package com.autumn.service.impl;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Article;
import com.autumn.domain.entity.vo.HotArticleVo;
import com.autumn.mapper.ArticleMapper;
import com.autumn.service.ArticleService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.autumn.constants.SystemConstants.ARTICLE_MAXIMUM_NUMBER;
import static com.autumn.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/25 23:00
 */
@Component
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1,ARTICLE_MAXIMUM_NUMBER);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        //bean拷贝
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }

        return ResponseResult.okResult(vs);
    }
}
