package com.jjzhou.dingtalkrobot.manage.impl;

import com.google.common.collect.Lists;
import com.jjzhou.dingtalkrobot.dao.ArticleMapper;
import com.jjzhou.dingtalkrobot.manage.ArticleManageService;
import com.jjzhou.dingtalkrobot.manage.UserManageService;
import com.jjzhou.dingtalkrobot.model.dto.ArticleDTO;
import com.jjzhou.dingtalkrobot.model.entity.ArticleDO;
import com.jjzhou.dingtalkrobot.model.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jjzhou
 * @date 2020/11/29 3:46 下午
 * @description
 */
@Slf4j
@Service
public class ArticleManageServiceImpl implements ArticleManageService {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ArticleMapper articleMapper;

    @Autowired
    private UserManageService userManageService;

    @Override
    public List<ArticleDTO> listToday() {
        List<ArticleDO> articleDOList = articleMapper.listTodayBlog();
        if (CollectionUtils.isEmpty(articleDOList)) {
            return Lists.newArrayList();
        }
        Map<Long, UserDO> userMap = userManageService.getAllUserMap();
        return articleDOList.stream()
                .filter(Objects::nonNull)
                .map(article -> {
                    ArticleDTO dto = new ArticleDTO();
                    UserDO userDO = userMap.get(article.getUserId());
                    dto.setId(article.getId());
                    dto.setUserId(article.getUserId());
                    dto.setPhone(Optional.ofNullable(userDO).map(UserDO::getPhone).orElse(null));
                    dto.setName(Optional.ofNullable(userDO).map(UserDO::getName).orElse(null));
                    dto.setNickName(Optional.ofNullable(userDO).map(UserDO::getNickName).orElse(null));
                    dto.setTitle(article.getTitle());
                    dto.setLink(article.getLink());
                    dto.setCreateTime(article.getCreateTime());
                    dto.setUpdateTime(article.getUpdateTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public int submitArticle(Long userId, String title, String link) {
        if (Objects.isNull(userId) || StringUtils.isEmpty(title) || StringUtils.isEmpty(link)) {
            return 0;
        }
        return articleMapper.insertOrUpdateBlog(userId, title, link);
    }
}
