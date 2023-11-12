package com.wenxuan.zhihuspider.service;

import java.util.List;
import java.util.Set;

/**
 * @author 文轩
 */
public interface AssociationalWordServer {

    /**
     * 初始化
     */
    void init();

    /**
     * 联想词表初始化
     */
    void initAssociationalWord();




    /**
     * 将关键词分析为关联词
     */
    Set<String> analysis(String word);


    /**
     * 将关键词分析为关联词
     */
    Set<String> analysis(List<String> words);

    /**
     * 将关键词分析为关联词
     */
    Set<String> analysis(List<String> words, int limit);

    /**
     * 切割
     */
    List<String> split(String word);


    /**
     * 获取相对长度
     */
    int getRelativeLength(String word);

    /**
     * 过滤关联词
     */
    Set<String> filter(Set<String> words);

    /**
     * 过滤关联词
     */
    boolean filter(String word);

    /**
     * 命中
     */
    void hit(String searchWord, Long associationalWordId);


}
