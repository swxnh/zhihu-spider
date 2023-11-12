package com.wenxuan.zhihuspider.mapper;

import com.wenxuan.zhihuspider.pojo.Column;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 文轩
* @description 针对表【t_column】的数据库操作Mapper
* @createDate 2023-09-26 14:28:50
* @Entity com.wenxuan.pgsql.pojo.Column
*/
public interface ColumnMapper {

    /**
     * 查询专栏是否存在
     * @param zhihuColumnId 知乎专栏id
     * @return 数据库中存在的数量
     */
    int existByZhihuColumnId(String zhihuColumnId);

    /**
     * 插入专栏信息
     * @param column 专栏信息
     */
    void insert(Column column);

    /**
     * 根据知乎专栏id更新
     * @param column 专栏信息
     */
    void updateByZhihuColumnId(Column column);

    /**
     * 获取爬取次数小于spiderCount的专栏id,数量为limit
     * @param spiderCount 爬取次数
     * @param limit 数量
     * @return 专栏id列表
     */
    List<String> listColumnIdWithSpiderCountLessThan(@Param("spiderCount") int spiderCount, @Param("limit") int limit);

    /**
     * 根据id增加爬取次数
     * @param zhihuColumnId 专栏id
     */
    void plusSpiderCountByColumnId(String zhihuColumnId);


    List<String> selectTitlePage(@Param("offset") int offset, @Param("limit") int limit);
}




