/**
  * Copyright 2023 bejson.com 
  */
package com.wenxuan.zhihuspider.spider.pojo.columns;

import lombok.Data;

/**
 * Auto-generated: 2023-09-26 9:59:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Badge {

    private String type;
    private String description;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

}