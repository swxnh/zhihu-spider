package com.wenxuan.zhihuspider.mapper;

import com.wenxuan.zhihuspider.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 文轩
* @description 针对表【t_paper】的数据库操作Mapper
* @createDate 2023-09-28 16:39:27
* @Entity com.wenxuan.pgsql.pojo.Paper
*/
public interface PaperMapper {

    /**
     * 根据知乎文章id判断是否存在
     * @param zhihuPaperId 知乎文章id
     * @return 是否存在
     */
    boolean existsWithZhuhuPaperId(Long zhihuPaperId);

    /**
     * 插入
     * @param paper 文章
     */
    void insert(Paper paper);

    /**
     * 根据知乎文章id更新
     * @param paper 文章
     */
    void updateByZhuhuPaperId(Paper paper);

    /**
     * 获取爬取次数小于count的专栏id,数量为limit
     * @param spiderCount 爬取次数
     * @return 专栏id列表
     */
    List<String> listColumnIdWithSpiderCountLessThan(@Param("spiderCount") int spiderCount, @Param("limit") int limit);
}




