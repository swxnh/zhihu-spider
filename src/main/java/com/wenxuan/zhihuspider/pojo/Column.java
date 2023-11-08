package com.wenxuan.zhihuspider.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_column
 */
@Data
public class Column implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 是否允许投稿
     */
    private Boolean acceptSubmission;

    /**
     * 内容数量
     */
    private Integer itemsCount;

    /**
     * 专栏类型
     */
    private String columnType;

    /**
     * 专栏标题
     */
    private String title;

    /**
     * 专栏链接
     */
    private String url;

    /**
     * 评论权限
     */
    private String commentPermission;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 专栏简介
     */
    private String intro;

    /**
     * 专栏图片
     */
    private String imageUrl;

    /**
     * 关注人数
     */
    private Integer followers;

    /**
     * 文章数
     */
    private Integer articlesCount;

    /**
     * 类型
     */
    private String type;

    /**
     * 知乎专栏id
     */
    private String zhihuColumnId;

    /**
     * 点赞数
     */
    private Integer voteupCount;

    /**
     * 爬取次数
     */
    private Integer spiderCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}