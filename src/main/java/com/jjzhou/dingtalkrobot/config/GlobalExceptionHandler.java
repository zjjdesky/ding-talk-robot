package com.jjzhou.dingtalkrobot.config;

import com.jjzhou.dingtalkrobot.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jjzhou
 * @date 2020/11/28 10:22 下午
 * @description
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> Result<T> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("发生异常！原因是:{}", e);
        return Result.fail(500, e.getMessage());
    }
}
