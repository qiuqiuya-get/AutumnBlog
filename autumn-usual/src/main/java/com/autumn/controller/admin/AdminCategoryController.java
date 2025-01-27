package com.autumn.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddCategoryDto;
import com.autumn.domain.entity.Category;
import com.autumn.domain.vo.CategoryUpdateVo;
import com.autumn.domain.vo.CategoryVo;
import com.autumn.domain.vo.ExcelCategoryVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.enums.AppHttpCodeEnum;
import com.autumn.service.CategoryService;
import com.autumn.utils.BeanCopyUtils;
import com.autumn.utils.WebUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 分类
 */
@RestController
@RequestMapping("/content/category")
public class AdminCategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult<Object> listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult<Object> result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }


    @GetMapping("list")
    public ResponseResult<Object> categoryList(Integer pageNum, Integer pageSize, String name, Integer status) {
        return categoryService.categoryList(pageNum, pageSize, name, status);
    }

    @PutMapping("changeStatus")
    public ResponseResult<Object> changeStatus(@RequestBody RoleChangeVo roleChangeVo) {
        return categoryService.changeStatus(roleChangeVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteRole(@PathVariable("id") Long id) {
        return categoryService.deleteRole(id);
    }

    @PostMapping
    public ResponseResult<Object> addTag(@RequestBody AddCategoryDto addCategoryDto) {
        Category category = BeanCopyUtils.copyBean(addCategoryDto, Category.class);
        return categoryService.addTag(category);
    }

    @GetMapping("/{id}")
    public ResponseResult<Object> updateGetOne(@PathVariable("id") Long id) {
        CategoryUpdateVo categoryUpdateVo = categoryService.getOneUpdate(id);
        return new ResponseResult<>(200, "操作成功", categoryUpdateVo);
    }

    @PutMapping
    public ResponseResult<Object> updateCategory(@RequestBody CategoryUpdateVo categoryUpdateVo) {
        Category category = BeanCopyUtils.copyBean(categoryUpdateVo, Category.class);
        return categoryService.updateTag(category);
    }

}