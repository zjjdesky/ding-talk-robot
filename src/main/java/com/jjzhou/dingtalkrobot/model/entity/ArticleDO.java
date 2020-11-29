package com.jjzhou.dingtalkrobot.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author jjzhou
 * @date 2020/11/28 11:11 上午
 * @description 文章
 */
@Data
@ToString
public class ArticleDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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
