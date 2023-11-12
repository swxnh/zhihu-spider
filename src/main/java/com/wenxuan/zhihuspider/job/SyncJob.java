package com.wenxuan.zhihuspider.job;

import com.wenxuan.zhihuspider.service.EsPaperService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Profile("prod")
@Component
public class SyncJob {

    private final EsPaperService esPaperService;

    public SyncJob(EsPaperService esPaperService) {
        this.esPaperService = esPaperService;
    }


    /**
     * 每6小时同步一次数据,从凌晨1时10分开始每隔3小时执行
     *
     */
    @Scheduled(cron = "0 10 1/3 * * ?")
    public void syncData() {
        esPaperService.syncData();
    }
}
