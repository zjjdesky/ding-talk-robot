package com.jjzhou.dingtalkrobot.manage.impl;

import com.google.common.collect.Maps;
import com.jjzhou.dingtalkrobot.dao.UserMapper;
import com.jjzhou.dingtalkrobot.manage.UserManageService;
import com.jjzhou.dingtalkrobot.model.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jjzhou
 * @date 2020/11/29 3:47 下午
 * @description
 */
@Slf4j
@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    @Override
    public Map<Long, UserDO> getAllUserMap() {
        List<UserDO> userDOList = userMapper.listAllUser();
        if (CollectionUtils.isEmpty(userDOList)) {
            return Maps.newHashMap();
        }
        return userDOList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(UserDO::getId, e -> e, (oldVar, newVar) -> newVar));
    }
}
