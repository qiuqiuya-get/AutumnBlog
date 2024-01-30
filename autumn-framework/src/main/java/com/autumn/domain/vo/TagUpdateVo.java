package com.autumn.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateVo {
    private Long id;

    //标签名
    private String name;

    // 标签备注
    private String remark;

}