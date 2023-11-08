/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.zhihuuser;

import java.util.List;

/**
 * 关注者数据
 * Auto-generated: 2023-09-12 9:47:24
 * @author 文轩
 */
@lombok.Data
public class FollowerData {

    private String id;
    private String url_token;
    private String name;
    private boolean use_default_avatar;
    private String avatar_url;
    private String avatar_url_template;
    private boolean is_org;
    private String type;
    private String url;
    private String user_type;
    private String headline;
    private String headline_render;
    private int gender;
    private boolean is_advertiser;
    private String ip_info;
    private Vip_info vip_info;
    private List<String> badge;
    private Badge_v2 badge_v2;
    private boolean is_following;
    private boolean is_followed;
    private int follower_count;
    private int answer_count;
    private int articles_count;
    private int available_medals_count;
    private boolean is_realname;
    private boolean has_applying_column;

}