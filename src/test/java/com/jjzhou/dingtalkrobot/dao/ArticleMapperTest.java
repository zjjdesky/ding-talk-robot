package com.jjzhou.dingtalkrobot.dao;

import com.jjzhou.dingtalkrobot.model.entity.ArticleDO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author jjzhou
 * @date 2020/12/5 1:45 下午
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Before
    public void setUp() throws Exception {
        System.out.println("test start...");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("test end ...");
    }

    @Test
    public void listTodayBlog() {
        List<ArticleDO> articleDOList = articleMapper.listTodayBlog();
        System.out.println(articleDOList);
        Assert.notNull(articleDOList);
    }
}
