package com.autumn.domain.entity.vo;

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
public class CategoryVo {
    private Long id;
    // 分类名
    private String name;
}
