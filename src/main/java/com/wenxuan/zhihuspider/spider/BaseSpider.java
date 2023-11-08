package com.wenxuan.zhihuspider.spider;

import com.alibaba.fastjson2.JSON;
import com.wenxuan.zhihuspider.spider.pojo.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 文轩
 */
@Slf4j
public abstract class BaseSpider<T extends BasePage<?>> implements Spider<T> {



    /**
     * 爬取一页数据
     *
     * @param params 爬虫参数
     * @param offset 偏移量
     * @return 爬虫返回的数据
     */
    public T spiderPage(String params, int offset, int limit){
        String url = getUrl(params, offset, limit);
        log.info(getStartLogTemplate(),url);
        //请求
        Connection.Response response = null;
        try {
            response = Jsoup
                    .connect(url)
                    .headers(getHeaders())
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) ")
                    .maxBodySize(0)
                    .execute();
        }catch (IOException e) {
            log.error(getErrorLogTemplate(), url, e);
            return null;
        }
        if (response.statusCode() != 200) {
            log.error(getFailLogTemplate(), url, response.statusCode());
            return null;
        }

        //获取set-cookie
        String cookie = response.header("set-cookie");
        //设置cookie
        if (!"".equals(cookie)) {
            setCookie(cookie);
        }

        //暂停1-5秒
        try {
            Thread.sleep((long) (Math.random() * 4000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //解析并返回结果

        String result = response.body();
//        替换windows换行符
//        result = result.replaceAll("\r\n", "\n");
//        try {
//            objectMapper.readValue(result, getClazz());
//            return objectMapper.readValue(result, getClazz());
//        } catch (JsonProcessingException e) {
//            log.error("json解析失败", e);
//            return null;
//        }

//        System.out.println(result);
        //转义

        return JSON.parseObject(result, getClazz());
    }


    /**
     * 爬取所有数据
     *
     * @param params 爬虫参数
     */
    public void spiderAll(String params){
        int offset = 0;
        int limit = 10;
        T page = spiderPage(params, offset, limit);
        if (page == null) {
            return;
        }
        handlerPage(page,params);
        while (!page.getPaging().getIs_end()) {
            offset += limit;
            page = spiderPage(params, offset, limit);
            if (page == null) {
                return;
            }
            handlerPage(page,params);
        }
    }

    @Override
    public void setCookie(String cookie) {
        Map<String, String> headers = getHeaders();

        String cookies = headers.get("Cookie");

        if (cookies == null) {
            cookies = "";
        }
        //转换为map
        Map<String, String> cookieMap = new HashMap<>();
        String[] split = cookies.split(";");
        for (String s : split) {
            String[] split1 = s.split("=");
            cookieMap.put(split1[0], split1[1]);
        }
        for (String s : cookie.split(";")) {
            String[] split1 = s.split("=");
            cookieMap.put(split1[0], split1[1]);
        }
//        cookieMap.remove("Path");
        //拼接
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        String newCookie = sb.toString();
        log.info("设置cookie: {}", newCookie);
        headers.put("Cookie", newCookie);
    }
}
