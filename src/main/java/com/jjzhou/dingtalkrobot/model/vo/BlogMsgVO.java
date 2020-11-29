package com.jjzhou.dingtalkrobot.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author jjzhou
 * @date 2020/11/28 9:19 下午
 * @description
 */
@Data
@ToString
public class BlogMsgVO {

    private Long userId;

    private Long name;

    private String nickName;

    private String title;

    private String blogLink;

}
