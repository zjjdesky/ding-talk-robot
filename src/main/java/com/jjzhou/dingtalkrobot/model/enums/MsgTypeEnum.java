package com.jjzhou.dingtalkrobot.model.enums;

/**
 * @author jjzhou
 * @date 2020/11/28 9:59 下午
 * @description
 */
public enum MsgTypeEnum {

    TEXT("text"),
    LINK("link"),
    MARKDOWN("markdown"),
    ACTION_CARD("actionCard"),
    FEED_CARD("feedCard")
    ;

    /**
     * 消息类型
     */
    private String type;

    MsgTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
