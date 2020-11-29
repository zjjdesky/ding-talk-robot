package com.jjzhou.dingtalkrobot.action;

import com.jjzhou.dingtalkrobot.common.model.Result;
import com.jjzhou.dingtalkrobot.service.HeadlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jjzhou
 * @date 2020/11/28 10:08 上午
 * @description 今日头条 主要推送今日已发布的博客
 */
@Api(tags = "今日头条")
@RestController("/headlineAction")
public class HeadlineAction {

    @Autowired
    private HeadlineService headlineService;

    @ApiOperation("推送今天的博客")
    @GetMapping("getTodayBlog")
    public Result<Boolean> pushTodayBlog() {
        headlineService.pushTodayBlog();
        return Result.success(true);
    }

    @ApiOperation("提交文章")
    @PostMapping("submitArticle")
    public Result<Boolean> submitArticle(@RequestParam(value = "userId", required = true) Long userId,
                                         @RequestParam(value = "title", required = true) String title,
                                         @RequestParam(value = "link", required = true) String link) {
        headlineService.submitArticle(userId, title, link);
        return Result.success(true);
    }
}
