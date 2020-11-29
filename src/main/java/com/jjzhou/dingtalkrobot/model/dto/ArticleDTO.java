package com.jjzhou.dingtalkrobot.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author jjzhou
 * @date 2020/11/29 3:52 下午
 * @description
 */
@Data
@ToString
public class ArticleDTO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客链接
     */
    private String link;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
