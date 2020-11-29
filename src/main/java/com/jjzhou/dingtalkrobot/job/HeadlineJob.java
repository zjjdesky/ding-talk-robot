package com.jjzhou.dingtalkrobot.job;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jjzhou.dingtalkrobot.manage.ArticleManageService;
import com.jjzhou.dingtalkrobot.manage.UserManageService;
import com.jjzhou.dingtalkrobot.model.dto.ArticleDTO;
import com.jjzhou.dingtalkrobot.model.entity.UserDO;
import com.jjzhou.dingtalkrobot.model.enums.MsgTypeEnum;
import com.jjzhou.dingtalkrobot.model.enums.RobotKeywordEnum;
import com.jjzhou.dingtalkrobot.service.HeadlineService;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jjzhou
 * @date 2020/11/29 11:13 下午
 * @description 今日头条任务
 */
@Slf4j
@Component
public class HeadlineJob {

    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private ArticleManageService articleManageService;

    @Autowired
    private UserManageService userManageService;

    @Value("${robot.link.first}")
    private String firstRobotOpenApiLink;

    @Scheduled(cron = "0 0,30 22,23 * * ?")
    public void pushToday() {
        headlineService.pushTodayBlog();
    }

    @Scheduled(cron = "0 0,30 22,23 * * ?")
    public void notifyNoPushUsers() {
        Map<Long, UserDO> allUserMap = userManageService.getAllUserMap();
        if (MapUtils.isEmpty(allUserMap)) {
            return;
        }
        List<String> waitSubmitUserMobiles =allUserMap.values().stream()
                .map(UserDO::getPhone)
                .collect(Collectors.toList());
        List<ArticleDTO> articleDTOList = articleManageService.listToday();
        if (CollectionUtils.isNotEmpty(articleDTOList)) {
            List<String> articlePhoneList = articleDTOList.stream().filter(Objects::nonNull).map(ArticleDTO::getPhone).collect(Collectors.toList());
            waitSubmitUserMobiles.removeIf(articlePhoneList::contains);
        }
        if (CollectionUtils.isEmpty(waitSubmitUserMobiles)) {
            return;
        }
        DingTalkClient client = new DefaultDingTalkClient(firstRobotOpenApiLink);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        at.setAtMobiles(waitSubmitUserMobiles);
        request.setAt(at);
        request.setMsgtype(MsgTypeEnum.TEXT.getType());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(RobotKeywordEnum.HEADLINE.getKeyword() + "\n" + "请抓紧提交文章哦~");
        request.setText(text);

        try {
            client.execute(request);
        } catch (ApiException e) {
            log.error("checkNoPushUsers调用异常， Exception: ", e);
        }
    }


}
