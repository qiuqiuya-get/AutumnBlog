package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Link;
import com.autumn.domain.vo.LinkUpdateDto;
import com.autumn.domain.vo.LinkVo;
import com.autumn.domain.vo.PageVo;
import com.autumn.mapper.LinkMapper;
import com.autumn.service.LinkService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 友链(Link)表服务实现类
 *
 * @author qiuqiuya-auto
 * @since 2023-05-30 21:11:36
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult<Object> getAllLink() {
        //查询所有审核通过的
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult<Object> linkList(Integer pageNum, Integer pageSize, String name, Integer status) {
        //分页查询
        QueryWrapper<Link> QueryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(name)){
            QueryWrapper.like("name",name);
        }
        if (!Objects.isNull(status)) {
            QueryWrapper.eq("status", status);
        }

        Page<Link> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, QueryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> addTag(Link link) {
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public LinkUpdateDto getOneUpdate(Long id) {
        QueryWrapper<Link> queryWrapper = new QueryWrapper<Link>()
                .eq("id",id);
        Link link = getOne(queryWrapper);
        return BeanCopyUtils.copyBean(link, LinkUpdateDto.class);
    }

    @Override
    public ResponseResult<Object> updateTag(Link link) {
        UpdateWrapper<Link> updateWrapper = new UpdateWrapper<Link>()
                .eq("id",link.getId())
                .set("status",link.getStatus())
                .set("name", link.getName())
                .set("description",link.getDescription())
                .set("address",link.getAddress())
                .set("logo",link.getLogo());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> deleteLink(Long id) {
        UpdateWrapper<Link> updateWrapper = new UpdateWrapper<Link>()
                .eq("id",id)
                .set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }
}
