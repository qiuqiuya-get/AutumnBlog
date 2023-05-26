package com.autumn.mapper;

import com.autumn.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/25 22:33
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
