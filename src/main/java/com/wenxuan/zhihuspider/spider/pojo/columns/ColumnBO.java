/**
  * Copyright 2023 bejson.com 
  */
package com.wenxuan.zhihuspider.spider.pojo.columns;

import lombok.Data;

/**
 * Auto-generated: 2023-09-26 9:59:35
 *
 * @author bejson.com (i@bejson.com)
 */
@Data
public class ColumnBO {

    /**
     * 是否允许投稿
     */
    private Boolean accept_submission;

    /**
     * 内容数
     */
    private int items_count;

    /**
     * 专栏类型
     */
    private String column_type;

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
    private String comment_permission;

    /**
     * 作者信息
     */
    private Author author;

    /**
     * 更新时间
     */
    private long updated;

    /**
     * 专栏简介
     */
    private String intro;

    /**
     * 专栏图片
     */
    private String image_url;

    /**
     * 关注人数
     */
    private int followers;

    /**
     * 文章数
     */
    private int articles_count;

    /**
     * 类型
     */
    private String type;

    /**
     * 专栏id
     */
    private String id;

    /**
     * 点赞数
     */
    private int voteup_count;

}