package com.wenxuan.zhihuspider.spider;

import com.wenxuan.zhihuspider.converter.ZhiHuConverter;
import com.wenxuan.zhihuspider.mapper.ColumnMapper;
import com.wenxuan.zhihuspider.spider.pojo.papers.PapersPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 爬虫接口
 * @author 文轩
 */
@Component
@Slf4j
public class PaperSpiderAction {

    private final Spider<PapersPage> paperSpider;

    private final ZhiHuConverter zhiHuConverter;



    private final ColumnMapper columnMapper;




    public PaperSpiderAction(Spider<PapersPage> paperSpider,
                             ZhiHuConverter zhiHuConverter,
                             ColumnMapper columnMapper) {
        this.paperSpider = paperSpider;
        this.zhiHuConverter = zhiHuConverter;
        this.columnMapper = columnMapper;
    }

    public void action(int count) {
        log.info("开始爬取专栏文章");
        List<String> stringList = columnMapper.listColumnIdWithSpiderCountLessThan(count, 100);
        for (String columnId : stringList) {
            spider(columnId);
            //根据id增加爬取次数
            log.info("爬取成功，准备更新爬取次数: columnId={}", columnId);
            columnMapper.plusSpiderCountByColumnId(columnId);
        }
    }

    public void spider(String columnId) {
        log.info("开始爬取专栏文章, columnId={}", columnId);
        paperSpider.spiderAll(columnId);
        log.info("爬取专栏文章结束, columnId={}", columnId);
    }






}
