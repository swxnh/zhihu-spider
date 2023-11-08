/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.papers;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2023-09-28 14:30:15
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */

@Data
public class Author {

    private Boolean is_followed;
    private String avatar_url_template;
    private String uid;
    private String user_type;
    private Boolean is_following;
    private String type;
    private String url_token;
    private String id;
    private String description;
    private String name;
    private Boolean is_advertiser;
    private String headline;
    private int gender;
    private String url;
    private String avatar_url;
    private Boolean is_org;
    private List<Badge> badge;

}