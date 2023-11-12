package com.wenxuan.zhihuspider.spider;


import java.util.Map;

/**
 * 爬虫接口
 * @author 文轩
 * @param <T> 爬虫返回的数据类型
 */
public interface Spider<T> {

    /**
     * 获取爬虫开始日志模板
     * @return 日志模板
     */

    String getStartLogTemplate();

    /**
     * 获取爬虫失败日志模板
     * @return 日志模板
     */
    String getFailLogTemplate();

    /**
     * 获取爬虫错误日志模板
     * @return 日志模板
     */
    String getErrorLogTemplate();

    /**
     * 获取爬虫返回的数据类型
     * @return 爬虫返回的数据类型
     */
    Class<T> getClazz();

    /**
     * 获取爬虫请求头
     * @return 爬虫请求头
     */
    Map<String, String> getHeaders();


    /**
     * 组装爬虫前缀
     */
    String getUrl(String params, int offset, int limit);

    /**
     * 爬取一页数据
     *
     * @param params 爬虫参数
     * @param offset 偏移量
     *               @param limit 限制条数
     * @return 爬虫返回的数据
     */
    T spiderPage(String params,int offset, int limit);


    /**
     * 爬取所有数据
     *
     * @param params 爬虫参数
     */
    void spiderAll(String params);


    void handlerPage(T page,String params);

    void setCookie(Map<String,String> headers,String cookie);
}
