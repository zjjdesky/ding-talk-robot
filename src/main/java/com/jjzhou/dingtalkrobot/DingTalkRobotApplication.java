package com.jjzhou.dingtalkrobot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@MapperScan("com.jjzhou.dingtalkrobot.dao")
public class DingTalkRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingTalkRobotApplication.class, args);
    }

}
