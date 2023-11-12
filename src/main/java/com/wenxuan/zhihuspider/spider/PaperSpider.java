package com.wenxuan.zhihuspider.spider;

import com.wenxuan.zhihuspider.converter.ZhiHuConverter;
import com.wenxuan.zhihuspider.mapper.PaperMapper;
import com.wenxuan.zhihuspider.pojo.Paper;
import com.wenxuan.zhihuspider.properties.ZhihuSpiderProperties;
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


    private final PaperMapper paperMapper;

    private final ZhiHuConverter zhiHuConverter;

    public PaperSpider(PaperMapper paperMapper,
                       ZhiHuConverter zhiHuConverter,
                       ZhihuSpiderProperties zhihuSpiderProperties) {
        super(zhihuSpiderProperties);
        this.paperMapper = paperMapper;
        this.zhiHuConverter = zhiHuConverter;
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
