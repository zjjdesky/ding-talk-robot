package com.jjzhou.dingtalkrobot.dao;

import com.jjzhou.dingtalkrobot.model.entity.ArticleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jjzhou
 * @date 2020/11/28 11:36 上午
 * @description
 */
public interface ArticleMapper {

    /**
     * 获取今天的博客
     *
     * @return
     */
    List<ArticleDO> listTodayBlog();

    /**
     * 添加博客
     *
     * @param userId
     * @param title
     * @param link
     * @return
     */
    int insertOrUpdateBlog(@Param("userId") Long userId, @Param("title") String title, @Param("link") String link);
}
