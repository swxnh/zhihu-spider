package com.wenxuan.zhihuspider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.wenxuan.zhihuspider.mapper")
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableCaching
@ConfigurationPropertiesScan("com.wenxuan.zhihuspider.properties")
public class ZhihuSpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhihuSpiderApplication.class, args);
    }

}
