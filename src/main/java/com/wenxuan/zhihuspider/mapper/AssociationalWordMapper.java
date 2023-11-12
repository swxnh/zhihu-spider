package com.wenxuan.zhihuspider.mapper;

import com.wenxuan.zhihuspider.pojo.AssociationalWord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 文轩
* @description 针对表【t_associational_word】的数据库操作Mapper
* @createDate 2023-11-12 00:28:48
* @Entity com.wenxuan.zhihuspider.pojo.AssociationalWord
*/
public interface AssociationalWordMapper {

    void insert(String memberName);
    void insertBatch(@Param("associationalWords") List<String> associationalWords);

    Long selectIdByAssociationalWord(String associationalWord);

    List<AssociationalWord> selectDetailByPage(@Param("offset") int offset, @Param("limit") int limit);
}




