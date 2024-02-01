package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Category;
import com.autumn.domain.entity.Link;
import com.autumn.domain.vo.CategoryUpdateVo;
import com.autumn.domain.vo.LinkUpdateDto;
import com.autumn.domain.vo.LinkVo;
import com.autumn.domain.vo.PageVo;
import com.autumn.mapper.LinkMapper;
import com.autumn.service.LinkService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult linkList(Integer pageNum, Integer pageSize, String name, Integer status) {
        //分页查询
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(name)){
            lambdaQueryWrapper.like(Link::getName,name);
        }
        if (!Objects.isNull(status)) {
            lambdaQueryWrapper.eq(Link::getStatus, status);
        }

        Page<Link> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Link link) {
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public LinkUpdateDto getOneUpdate(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        Link link = getOne(queryWrapper);
        LinkUpdateDto linkUpdateDto = BeanCopyUtils.copyBean(link, LinkUpdateDto.class);
        return linkUpdateDto;
    }

    @Override
    public ResponseResult updateTag(Link link) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",link.getId());
        updateWrapper.set("status",link.getStatus());
        updateWrapper.set("name", link.getName());
        updateWrapper.set("description",link.getDescription());
        updateWrapper.set("address",link.getAddress());
        updateWrapper.set("logo",link.getLogo());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(Long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }
}
