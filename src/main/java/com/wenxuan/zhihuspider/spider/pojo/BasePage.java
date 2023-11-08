package com.wenxuan.zhihuspider.spider.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 文轩
 */

@Data
public class BasePage<T> implements java.io.Serializable{

    private Paging paging;
    private List<T> data;

    @Data
    public static class Paging implements java.io.Serializable{
        private Boolean is_end;
        private int totals;
        private String previous;
        private Boolean is_start;
        private String next;
    }
}
