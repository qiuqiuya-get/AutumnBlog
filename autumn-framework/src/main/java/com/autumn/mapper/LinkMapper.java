package com.autumn.mapper;

import com.autumn.domain.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author qiuqiuya-auto
 * @since 2023-05-30 21:11:34
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
