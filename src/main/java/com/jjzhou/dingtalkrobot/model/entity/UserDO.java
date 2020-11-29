package com.jjzhou.dingtalkrobot.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author jjzhou
 * @date 2020/11/28 11:28 上午
 * @description
 */
@Data
@ToString
public class UserDO {

    /**
     * 主键id
     */
    private Long id;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
