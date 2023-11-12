package com.wenxuan.zhihuspider.job;

import com.wenxuan.zhihuspider.spider.ColumnsSpiderAction;
import com.wenxuan.zhihuspider.spider.FollowersSpiderAction;
import com.wenxuan.zhihuspider.spider.PaperSpiderAction;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author 文轩
 */
@Profile("prod")
@Component
public class ZhiHuScheduling {

    private final FollowersSpiderAction followersSpiderAction;

    private final ColumnsSpiderAction columnsSpiderAction;

    private final PaperSpiderAction paperSpiderAction;

    public ZhiHuScheduling(FollowersSpiderAction followersSpiderAction,
                           ColumnsSpiderAction columnsSpiderAction,
                           PaperSpiderAction paperSpiderAction) {
        this.followersSpiderAction = followersSpiderAction;
        this.columnsSpiderAction = columnsSpiderAction;
        this.paperSpiderAction = paperSpiderAction;
    }

    /**
     * 每隔2小时的第22分钟执行
     */
    @Async
    @Scheduled(cron = "0 22 */2 * * ?")
    public void spider() {
        followersSpiderAction.action(1);
    }

    /**
     * 从1时开始每隔2小时的第37分钟执行
     */
    @Async
    @Scheduled(cron = "0 37 1/2 * * ?")
    public void columnSpider() {
        columnsSpiderAction.action(2);
    }

    /**
     * 每日凌晨1点、7时、13时、19时执行
     */
    @Async
    @Scheduled(cron = "0 0 1,7,13,19 * * ?")
    public void paperSpider() {
        paperSpiderAction.action(2);
    }
}
