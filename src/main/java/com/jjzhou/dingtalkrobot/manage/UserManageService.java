package com.jjzhou.dingtalkrobot.manage;

import com.jjzhou.dingtalkrobot.model.entity.UserDO;

import java.util.Map;

/**
 * @author jjzhou
 * @date 2020/11/29 3:46 下午
 * @description
 */
public interface UserManageService {

    /**
     * 获取所有用户信息
     * @return key: userId value: UserDO
     */
    Map<Long, UserDO> getAllUserMap();
}
