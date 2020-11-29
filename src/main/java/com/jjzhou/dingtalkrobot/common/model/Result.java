package com.jjzhou.dingtalkrobot.common.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author jjzhou
 * @date 2020-03-29 23:06
 * @description
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 25933375820459333L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 请求是否成功
     */
    private boolean success = false;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data = null;

    /**
     * 链路id
     */
    private String traceId;

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> res = new Result<>();
        res.setData(data);
        res.setCode(200);
        res.setSuccess(true);
        res.setMsg("操作成功");
        return res;
    }

    /**
     * 请求失败
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> res = new Result<>();
        res.setData(null);
        res.setCode(code);
        res.setSuccess(false);
        res.setMsg(StringUtils.isEmpty(msg) ? "操作失败" : msg);
        return res;
    }

}
