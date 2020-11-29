package com.jjzhou.dingtalkrobot.service.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jjzhou.dingtalkrobot.dao.ArticleMapper;
import com.jjzhou.dingtalkrobot.dao.UserMapper;
import com.jjzhou.dingtalkrobot.manage.ArticleManageService;
import com.jjzhou.dingtalkrobot.manage.UserManageService;
import com.jjzhou.dingtalkrobot.model.dto.ArticleDTO;
import com.jjzhou.dingtalkrobot.model.entity.ArticleDO;
import com.jjzhou.dingtalkrobot.model.entity.UserDO;
import com.jjzhou.dingtalkrobot.model.enums.MsgTypeEnum;
import com.jjzhou.dingtalkrobot.model.enums.RobotKeywordEnum;
import com.jjzhou.dingtalkrobot.service.HeadlineService;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jjzhou
 * @date 2020/11/28 10:15 上午
 * @description
 */
@Slf4j
@Service
public class HeadlineServiceImpl implements HeadlineService {

    @Value("${robot.link.first}")
    private String firstRobotOpenApiLink;

    @Autowired
    private ArticleManageService articleManageService;

    @Autowired
    private UserManageService userManageService;

    @Override
    public void pushTodayBlog() {
        List<ArticleDTO> articleDTOList = articleManageService.listToday();
        String content = null;
        if (CollectionUtils.isEmpty(articleDTOList)) {
            content = "今日还未提交文章，还请大家继续加油哦~\n";
        } else {
            content = getTodayContent(articleDTOList);
        }

        DingTalkClient client = new DefaultDingTalkClient(firstRobotOpenApiLink);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(true);
        request.setAt(at);
        request.setMsgtype(MsgTypeEnum.TEXT.getType());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(RobotKeywordEnum.HEADLINE.getKeyword() + "\n" + content + "\n");
        request.setText(text);

        try {
            client.execute(request);
        } catch (ApiException e) {
            log.error("pushTodayBlog异常，Exception: ", e);
        }
    }

    @Override
    public void submitArticle(Long userId, String title, String link) {
        if (Objects.isNull(userId) || StringUtils.isEmpty(title) || StringUtils.isEmpty(link)) {
            throw new IllegalArgumentException("入参为空，请重新填写！");
        }
        Map<Long, UserDO> allUserMap = userManageService.getAllUserMap();
        if (!allUserMap.containsKey(userId)) {
            throw new IllegalStateException("不存在的用户id，请重新填写！");
        }
        int count = articleManageService.submitArticle(userId, title, link);

        UserDO currentUserDO = allUserMap.get(userId);
        DingTalkClient client = new DefaultDingTalkClient(firstRobotOpenApiLink);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(MsgTypeEnum.TEXT.getType());
        String content = null;
        if (count <= 0) {
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setIsAtAll(false);
            at.setAtMobiles(Collections.singletonList(currentUserDO.getPhone()));
            request.setAt(at);
            content = "请勿重复提交哦~";
        } else {
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setIsAtAll(true);
            request.setAt(at);
            content = String.format("%s 提交了今日的博客\n标题：%s\n链接：%s\n",
                    currentUserDO.getNickName(), title, link);
        }
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(RobotKeywordEnum.HEADLINE.getKeyword() + "\n" + content);
        request.setText(text);

        try {
            client.execute(request);
        } catch (ApiException e) {
            log.error("pushTodayBlog异常，Exception: ", e);
        }
    }

    /**
     * 将文章DTO转化为text类型消息
     *
     * @param articleDTOList
     * @return
     */
    private String getTodayContent(List<ArticleDTO> articleDTOList) {
        return articleDTOList.stream()
                .filter(Objects::nonNull)
                .map(dto -> {
                    return String.format("%s(%s)：%s", dto.getTitle(), dto.getNickName(), dto.getLink());
                })
                .collect(Collectors.joining("\n"));
    }
}
