package com.wenxuan.zhihuspider.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * 请求头属性
 * @author 文轩
 */
@Data
@ConfigurationProperties(prefix = "zhihu-spider")
public class ZhihuSpiderProperties {

    List<Map<String,String>> headersList;



}
