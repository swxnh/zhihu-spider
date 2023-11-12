package com.wenxuan.zhihuspider;

import com.wenxuan.zhihuspider.spider.ColumnsSpiderAction;
import com.wenxuan.zhihuspider.spider.FollowersSpiderAction;
import com.wenxuan.zhihuspider.spider.PaperSpiderAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhihuSpiderApplicationTests {

    @Autowired
    FollowersSpiderAction followersSpiderAction;

    @Autowired
    ColumnsSpiderAction columnsSpiderAction;

    @Autowired
    PaperSpiderAction paperSpiderAction;

    @Test
    void contextLoads() {

//        followersSpiderAction.action(1);
//        columnsSpiderAction.action(2);
        paperSpiderAction.action(2);
    }

}
