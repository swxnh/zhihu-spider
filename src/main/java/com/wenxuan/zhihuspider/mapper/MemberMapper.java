package com.wenxuan.zhihuspider.mapper;

import com.wenxuan.zhihuspider.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 文轩
* @description 针对表【t_member】的数据库操作Mapper
* @createDate 2023-09-16 22:51:56
* @Entity com.wenxuan.pgsql.pojo.Member
*/
public interface MemberMapper {

    /**
     * 插入
     * @param member 实体对象
     */
    void insert(Member member);

    /**
     * 根据zhihuMemberId判断数据是否存在
     * @param zhihuMemberId 知乎用户id
     * @return 数据库中存在的数量
     */
    int existByZhihuMemberId(String zhihuMemberId);

    /**
     * 根据zhihuMemberId更新
     * @param member 实体对象
     */
    void updateByZhihuMemberId(Member member);

    /**
     * 根据count查询爬取次数小于count的用户
     * @param spiderCount 爬取次数
     * @return 用户列表
     */
    List<Member> selectSpiderCountLessThan(@Param("spiderCount") Integer spiderCount, @Param("limit") Integer limit);

    /**
     * 根据id增加爬取次数
     * @param id 用户id
     */
    void plusSpiderCountById(Long id);

    /**
     * 根据爬取次数查询用户
     * @param less 小于
     * @param more 大于
     * @param limit 限制
     * @return 用户列表
     */
    List<Member> selectSpiderCountBetween(@Param("less")int less, @Param("more")int more, @Param("limit")int limit);
}




