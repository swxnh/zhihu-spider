package com.wenxuan.zhihuspider.spider;

import com.wenxuan.zhihuspider.converter.ZhiHuConverter;
import com.wenxuan.zhihuspider.mapper.PaperMapper;
import com.wenxuan.zhihuspider.pojo.Paper;
import com.wenxuan.zhihuspider.spider.pojo.papers.PapersPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 文轩
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Component("paperSpider")
@Slf4j
@Data
public class PaperSpider extends BaseSpider<PapersPage> {

    private final static String URL = "https://www.zhihu.com/api/v4/columns/";

    private String startLogTemplate = "开始爬取专栏文章: url={}";

    private String failLogTemplate = "爬取专栏文章失败: url={}, statusCode={}";

    private String errorLogTemplate = "爬取专栏文章异常: url={}";

    private Class<PapersPage> clazz = PapersPage.class;

    private Map<String, String> headers;
//    ("Cookie", "_zap=79ae751a-7d38-4aa6-b448-05a0691caf7d; d_c0=ACDXe9FMRBaPTkQJMFHFZEgxQ1NIOLgRNUE=|1675320662; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1698583828,1698659105,1698739945,1698804793; YD00517437729195%3AWM_NI=PJ1Z3UwhI5J4V1LZSy7lOwW7LapM0lA5Uvx6ag8EhLc%2Bd54YjOefeyulOI5rtTbtS3Y9fmHvRMkzMh8HCBz%2B329YRDnk3%2F0DCtaQJkbjwBF%2Frc2QNPbpopJU%2FPGsxWAGZkg%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6ee9ac25f8e8ea2cceb25b0968fb3c85b928a8fb0c13bb6968494dc4fabb78daeef2af0fea7c3b92a9496ffbbb25fb5b887d6c94096ef84…7e2a3; YD00517437729195%3AWM_TID=ho%2FV0HrQWAVFVEQVBBORKRJU7DSN1AFo; __snaker__id=KvCZTznQRfvO5gnm; z_c0=2|1:0|10:1697444453|4:z_c0|80:MS4xN0FQMURRQUFBQUFtQUFBQVlBSlZUY0tCRTJZUjZ3Rk1GUTFkeWNlUThKV2hYbHd3aloxYndRPT0=|7e6e28deb7092b8f300f9d07d1555e3858fcf390fb66a883e836298200988969; q_c1=0045de7caf104e84a9b9538a7ebb40a5|1691480501000|1691480501000; _xsrf=FA6HuQZtM8mJlQpbA4wkk4Evf4ZHp2iu; KLBRSID=d017ffedd50a8c265f0e648afe355952|1698810100|1698809574; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1698810100; tst=f")
//            "x-zse-93", "101_3_3.0",
//            "x-zse-96", "2.0_",
//            "x-app-za", "OS=Web");


    private final PaperMapper paperMapper;

    private final ZhiHuConverter zhiHuConverter;

    public PaperSpider(PaperMapper paperMapper,
                       ZhiHuConverter zhiHuConverter) {
        this.paperMapper = paperMapper;
        this.zhiHuConverter = zhiHuConverter;
        this.headers = new HashMap<>();
        headers.put("Cookie", "");
        headers.put("x-zse-93", "101_3_3.0");
        headers.put("x-zse-96", "2.0_");
        headers.put("x-app-za", "OS=Web");
    }

    /**
     * 组装爬虫前缀
     *
     * @param params 爬虫参数
     * @param offset
     * @param limit
     */
    @Override
    public String getUrl(String params, int offset, int limit) {
        return URL + params + "/items" + "?limit=" + limit + "&offset=" + offset;
    }

    @Override
    public void handlerPage(PapersPage page, String params) {
        List<Paper> paperList = zhiHuConverter.toPaper(page.getData());
        for (Paper paper : paperList) {
            paper.setZhihuColumnId(params);
            handler(paper);
        }
    }


    private void handler(Paper paper) {
        //判断是否存在
        boolean exists = paperMapper.existsWithZhuhuPaperId(paper.getZhihuPaperId());
        if (!exists) {
            //不存在，插入
            log.info("插入文章: {}", paper.getZhihuPaperId());
            paperMapper.insert(paper);
        } else {
            //存在，更新
            log.info("更新文章: {}", paper.getZhihuPaperId());
            paperMapper.updateByZhuhuPaperId(paper);
        }
    }
}
