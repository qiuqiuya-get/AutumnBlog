package com.autumn.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/30 21:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkDto {
    @TableId
    private String name;
    private String logo;
    private String description;
    //网站地址
    private String address;
    private String status;

}
