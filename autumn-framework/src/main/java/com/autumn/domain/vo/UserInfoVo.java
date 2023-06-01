package com.autumn.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 11:03
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;
}