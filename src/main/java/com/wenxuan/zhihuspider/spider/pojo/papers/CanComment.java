/**
  * Copyright 2023 json.cn 
  */
package com.wenxuan.zhihuspider.spider.pojo.papers;

import lombok.Data;


/**
 * 是否可以评论
 * @author 文轩
 */
@Data
public class CanComment {

    private Boolean status;
    private String reason;


}