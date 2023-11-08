package com.wenxuan.zhihuspider.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_paper
 */
@Data
public class Paper implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 更新
     */
    private Long updated;

    /**
     * 是否已标记
     */
    private Boolean isLabeled;

    /**
     * 版权保护
     */
    private String copyrightPermission;

    /**
     * 是否开启目录
     */
    private Boolean tableOfContentsEnable;

    /**
     * 摘要
     */
    private String excerpt;

    /**
     * 管理员是否关闭评论区
     */
    private Boolean adminClosedComment;

    /**
     * 投票
     */
    private Integer voting;

    /**
     * 文章类型
     */
    private String articleType;

    /**
     * 理由
     */
    private String reason;

    /**
     * 摘要标题
     */
    private String excerptTitle;

    /**
     * 知乎专栏文章id
     */
    private Long zhihuPaperId;

    /**
     * 赞同数
     */
    private Integer voteupCount;

    /**
     * 标题图片url
     */
    private String titleImage;

    /**
     * 是否有专栏
     */
    private Boolean hasColumn;

    /**
     * 专栏地址
     */
    private String url;

    /**
     * 评论权限
     */
    private String commentPermission;

    /**
     * 作者id
     */
    private String zhihuMemberId;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private String state;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否可以评论
     */
    private Boolean canComment;

    /**
     * 不可以评论的理由
     */
    private String canCommentReason;

    /**
     * 文章类型
     */
    private String type;

    /**
     * 是否建议修改
     */
    private Boolean suggestEditStatus;

    /**
     * 建议地址
     */
    private String suggestEditUrl;

    /**
     * 要求修改的理由
     */
    private String suggestEditReason;

    /**
     * 提示
     */
    private String suggestEditTip;

    /**
     * 要求修改的标题
     */
    private String suggestEditTitle;

    /**
     * 专栏id
     */
    private String zhihuColumnId;

    /**
     * 爬取次数
     */
    private Integer spiderCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}