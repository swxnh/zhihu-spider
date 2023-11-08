package com.wenxuan.zhihuspider.spider;

import com.wenxuan.zhihuspider.converter.ZhiHuConverter;
import com.wenxuan.zhihuspider.mapper.ColumnMapper;
import com.wenxuan.zhihuspider.mapper.MemberMapper;
import com.wenxuan.zhihuspider.pojo.Column;
import com.wenxuan.zhihuspider.spider.pojo.columns.ColumnBO;
import com.wenxuan.zhihuspider.spider.pojo.columns.ColumnInfo;
import com.wenxuan.zhihuspider.spider.pojo.columns.ColumnsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专栏爬虫
 * @author 文轩
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Slf4j
@Data
public class ColumnsSpider extends BaseSpider<ColumnsPage> {

    private final String startLogTemplate = "开始爬取专栏: url={}";

    private final String failLogTemplate = "爬取专栏失败: url={}, statusCode={}";

    private final String errorLogTemplate = "爬取专栏异常: url={}";

    private final Class<ColumnsPage> clazz = ColumnsPage.class;

    private final Map<String, String> headers;




    private final ZhiHuConverter zhiHuConverter;

    private final ColumnMapper columnMapper;

    private final MemberMapper memberMapper;

    public ColumnsSpider(ZhiHuConverter zhiHuConverter,
                         ColumnMapper columnMapper,
                         MemberMapper memberMapper) {
        this.zhiHuConverter = zhiHuConverter;
        this.columnMapper = columnMapper;
        this.memberMapper = memberMapper;
        headers = new HashMap<>(4);
        headers.put("Cookie","");
        headers.put("x-zse-93","101_3_3.0");
        headers.put("x-zse-96","2.0_");
        headers.put("x-app-za","OS=Web");
    }



    /**
     * 组装爬虫前缀
     *
     * @param params
     * @param offset
     * @param limit
     */
    @Override
    public String getUrl(String params, int offset, int limit) {
        return "https://www.zhihu.com/api/v4/members/" + params + "/column-contributions?include=data[*].column.intro,followers,articles_count,voteup_count,items_count&offset=" + offset + "&limit=" + limit;
    }

    @Override
    public void handlerPage(ColumnsPage page, String params) {
        //提取取data列表中的columns作为list
        List<ColumnInfo> data = page.getData();
        List<ColumnBO> columnBOList = new ArrayList<>(data.size());
        for (ColumnInfo columnInfo : data) {
            columnBOList.add(columnInfo.getColumn());
        }
        List<Column> columnsList = zhiHuConverter.toColumn(columnBOList);
        handlerList(columnsList);

    }

    private void handlerList(List<Column> columnsList) {
        for (Column column : columnsList) {
            handlerData(column);
        }
    }

    private void handlerData(Column column) {
        int exist = columnMapper.existByZhihuColumnId(column.getZhihuColumnId());
        if (exist == 0) {
            log.info("插入专栏， name={} zhihuColumnId={}", column.getTitle(), column.getZhihuColumnId());
            columnMapper.insert(column);
        } else {
            log.info("更新专栏，name={} zhihuColumnId={}", column.getTitle(), column.getZhihuColumnId());
            columnMapper.updateByZhihuColumnId(column);
        }
    }
}
