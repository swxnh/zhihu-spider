/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.papers;

import lombok.Data;

/**
 * Auto-generated: 2023-09-28 14:30:15
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class PaperBO {

    private long updated;
    private Boolean is_labeled;
    private String copyright_permission;
    private Settings settings;
    private String excerpt;
    private Boolean admin_closed_comment;
    private int voting;
    private String article_type;
    private String reason;
    private String excerpt_title;
    private long id;
    private int voteup_count;
    private String title_image;
    private Boolean has_column;
    private String url;
    private String comment_permission;
    private Author author;
    private int comment_count;
    private long created;
    private String content;
    private String state;
    private String image_url;
    private String title;
    private CanComment can_comment;
    private String type;
    private SuggestEdit suggest_edit;

}