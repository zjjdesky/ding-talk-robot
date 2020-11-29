package com.jjzhou.dingtalkrobot.manage;

import com.jjzhou.dingtalkrobot.model.dto.ArticleDTO;

import java.util.List;

/**
 * @author jjzhou
 * @date 2020/11/29 3:46 下午
 * @description
 */
public interface ArticleManageService {

    /**
     * 获取今日文章
     *
     * @return
     */
    List<ArticleDTO> listToday();

    /**
     * 提交文章
     *
     * @param userId
     * @param title
     * @param link
     * @return
     */
    int submitArticle(Long userId, String title, String link);
}
