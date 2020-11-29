package com.jjzhou.dingtalkrobot.dao;

import com.jjzhou.dingtalkrobot.model.entity.UserDO;

import java.util.List;

/**
 * @author jjzhou
 * @date 2020/11/28 11:36 上午
 * @description
 */
public interface UserMapper {

    /**
     * 获取所有的用户
     *
     * @return
     */
    List<UserDO> listAllUser();
}
