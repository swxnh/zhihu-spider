package com.wenxuan.zhihuspider.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @author 文轩
 * @TableName t_member
 */
@Data
public class Member implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 知乎用户id
     */
    private String zhihuMemberId;

    /**
     * 访问url时的token
     */
    private String urlToken;

    /**
     * 用户名
     */
    private String name;

    /**
     * 是否使用默认头像
     */
    private Boolean useDefaultAvatar;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 头像模板地址(用户上传原图)
     */
    private String avatarUrlTemplate;

    /**
     * 是否是组织
     */
    private Boolean isOrg;

    /**
     * 账号类型
     */
    private String type;

    /**
     * 主页地址
     */
    private String url;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 简介
     */
    private String headline;

    /**
     * 简介缩略
     */
    private String headlineRender;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 是否是广告商
     */
    private Boolean isAdvertiser;

    /**
     * ip信息
     */
    private String ipInfo;

    /**
     * 关注者数
     */
    private Integer followerCount;

    /**
     * 回答数
     */
    private Integer answerCount;

    /**
     * 文章数
     */
    private Integer articlesCount;

    /**
     * 是否是真名
     */
    private Boolean isRealname;

    /**
     * 是否具有应用列
     */
    private Boolean hasApplyingColumn;

    /**
     * 爬取次数
     */
    private Integer spiderCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Serial
    private static final long serialVersionUID = 1L;


}