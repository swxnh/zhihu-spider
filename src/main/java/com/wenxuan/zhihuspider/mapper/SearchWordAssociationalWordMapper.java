package com.wenxuan.zhihuspider.mapper;

import com.wenxuan.zhihuspider.pojo.SearchWordAssociationalWord;

import java.util.Collection;
import java.util.Set;

/**
* @author 文轩
* @description 针对表【t_search_word_associational_word】的数据库操作Mapper
* @createDate 2023-11-12 00:28:53
* @Entity com.wenxuan.zhihuspider.pojo.SearchWordAssociationalWord
*/
public interface SearchWordAssociationalWordMapper {

    void insertBatch(Long associationalWordId, Collection<String> searchWords);

    void plusScore(String searchWord, Long associationalWordId);

    /**
     * 删除创建时间大于等于指定时间的数据
     */
    void deleteByCreateTimeLessThanEqual(Long createTime);

}




