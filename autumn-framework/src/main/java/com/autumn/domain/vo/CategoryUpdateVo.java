package com.autumn.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/28 16:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateVo {
    private Long id;
    // 分类名
    private String name;
    //描述
    private String description;
    private String status;
}
