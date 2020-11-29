package com.jjzhou.dingtalkrobot.model.enums;

import lombok.Getter;

/**
 * @author jjzhou
 * @date 2020/11/28 10:00 上午
 * @description 机器人关键词
 */
@Getter
public enum RobotKeywordEnum {

    HEADLINE(1, "今日头条", "机器人推送今日已发布的博客文章"),
    EXCELLENT_BLOG(2, "好文推送", "推送一些大佬写的优秀的博客文章")
    ;

    /**
     * 关键词code
     */
    private Integer code;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 关键词描述
     */
    private String desc;

    RobotKeywordEnum(Integer code, String keyword, String desc) {
        this.code = code;
        this.keyword = keyword;
        this.desc = desc;
    }
}
