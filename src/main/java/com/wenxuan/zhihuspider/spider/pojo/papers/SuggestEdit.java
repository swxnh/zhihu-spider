/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.papers;

import lombok.Data;

/**
 * @author 文轩
 * 是否建议修改
 */
@Data
public class SuggestEdit {

    private Boolean status;
    private String url;
    private String reason;
    private String tip;
    private String title;

}