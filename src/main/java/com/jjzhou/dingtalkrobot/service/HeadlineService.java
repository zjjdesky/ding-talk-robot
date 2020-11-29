package com.jjzhou.dingtalkrobot.service;

/**
 * @author jjzhou
 * @date 2020/11/28 10:15 上午
 * @description
 */
public interface HeadlineService {

    /**
     * 推送今天的博客
     */
    void pushTodayBlog();

    /**
     * 提交文章
     *
     * @param userId
     * @param title
     * @param link
     */
    void submitArticle(Long userId, String title, String link);
}
