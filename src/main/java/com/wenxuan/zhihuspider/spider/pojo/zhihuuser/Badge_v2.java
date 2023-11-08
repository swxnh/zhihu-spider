/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.zhihuuser;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2023-09-12 9:47:24
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class Badge_v2 {

    private String title;
    private List<String> merged_badges;
    private List<String> detail_badges;
    private String icon;
    private String night_icon;

}