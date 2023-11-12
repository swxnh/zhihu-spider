package com.wenxuan.zhihuspider.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 文轩
 */

@Document(indexName = "paper")
@Data
@Setting(settingPath = "es-setting.json",replicas = 0)
public class EsPaper implements Serializable {
    /**
     * 主键
     */
    @Id
    @Field(index = false,type = FieldType.Long)
    private Long id;

    /**
     * 更新
     */
    @Field(index = false,type = FieldType.Long)
    private Long updated;

    /**
     * 是否已标记
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean isLabeled;

    /**
     * 版权保护
     */
    @Field(index = false,type = FieldType.Text)
    private String copyrightPermission;

    /**
     * 是否开启目录
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean tableOfContentsEnable;

    /**
     * 摘要
     */
    @Field(type = FieldType.Text)
    private String excerpt;

    /**
     * 管理员是否关闭评论区
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean adminClosedComment;

    /**
     * 投票
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer voting;

    /**
     * 文章类型
     */
    @Field(index = false,type = FieldType.Text)
    private String articleType;

    /**
     * 理由
     */
    @Field(index = false,type = FieldType.Text)
    private String reason;

    /**
     * 摘要标题
     */
    @Field(type = FieldType.Text)
    private String excerptTitle;

    /**
     * 知乎专栏文章id
     */
    @Field(index = false,type = FieldType.Long)
    private Long zhihuPaperId;

    /**
     * 赞同数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer voteupCount;

    /**
     * 标题图片url
     */
    @Field(index = false,type = FieldType.Text)
    private String titleImage;

    /**
     * 是否有专栏
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean hasColumn;

    /**
     * 专栏地址
     */
    @Field(index = false,type = FieldType.Text)
    private String url;

    /**
     * 评论权限
     */
    @Field(index = false,type = FieldType.Text)
    private String commentPermission;

    /**
     * 作者id
     */
    @Field(index = false,type = FieldType.Text)
    private String zhihuMemberId;

    /**
     * 评论数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer commentCount;

    /**
     * 创建时间
     */
    @Field(index = false,type = FieldType.Long)
    private Long created;

    /**
     * 内容
     * 设置html过滤器
     * 标准分词器
     */
    @Field(type = FieldType.Text,analyzer = "html_analyzer")
    private String content;

    /**
     * 状态
     */
    @Field(index = false,type = FieldType.Text)
    private String state;

    /**
     * 图片地址
     */
    @Field(index = false,type = FieldType.Text)
    private String imageUrl;

    /**
     * 标题
     */
    @Field(type = FieldType.Text)
    private String title;

    /**
     * 是否可以评论
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean canComment;

    /**
     * 不可以评论的理由
     */
    @Field(index = false,type = FieldType.Text)
    private String canCommentReason;

    /**
     * 文章类型
     */
    @Field(index = false,type = FieldType.Text)
    private String type;

    /**
     * 是否建议修改
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean suggestEditStatus;

    /**
     * 建议地址
     */
    @Field(index = false,type = FieldType.Text)
    private String suggestEditUrl;

    /**
     * 要求修改的理由
     */
    @Field(index = false,type = FieldType.Text)
    private String suggestEditReason;

    /**
     * 提示
     */
    @Field(index = false,type = FieldType.Text)
    private String suggestEditTip;

    /**
     * 要求修改的标题
     */
    @Field(index = false,type = FieldType.Text)
    private String suggestEditTitle;

    /**
     * 专栏id
     */
    @Field(index = false,type = FieldType.Text)
    private String zhihuColumnId;


    /**
     * 创建时间
     */
    @Field(index = false,type = FieldType.Date)
    private Date createTime;

    /**
     * 修改时间
     */
    @Field(index = false,type = FieldType.Date)
    private Date updateTime;



//    -------------------------作者信息列--------------------------------
    /**
     * 访问url时的token
     */
    @Field(type = FieldType.Text)
    private String urlToken;

    /**
     * 用户名
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 是否使用默认头像
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean useDefaultAvatar;

    /**
     * 头像地址
     */
    @Field(index = false,type = FieldType.Text)
    private String avatarUrl;

    /**
     * 头像模板地址(用户上传原图)
     */
    @Field(index = false,type = FieldType.Text)
    private String avatarUrlTemplate;

    /**
     * 是否是组织
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean isOrg;

    /**
     * 账号类型
     */
    @Field(index = false,type = FieldType.Text)
    private String accountType;

    /**
     * 主页地址
     */
    @Field(index = false,type = FieldType.Text)
    private String userUrl;

    /**
     * 用户类型
     */
    @Field(index = false,type = FieldType.Text)
    private String userType;

    /**
     * 简介
     */
    @Field(type = FieldType.Text)
    private String headline;

    /**
     * 简介缩略
     */
    @Field(type = FieldType.Text)
    private String headlineRender;

    /**
     * 性别
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer gender;

    /**
     * 是否是广告商
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean isAdvertiser;

    /**
     * ip信息
     */
    @Field(index = false,type = FieldType.Text)
    private String ipInfo;

    /**
     * 关注者数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer followerCount;

    /**
     * 回答数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer answerCount;

    /**
     * 文章数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer articlesCount;

    /**
     * 是否是真名
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean isRealname;

    /**
     * 是否具有应用列
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean hasApplyingColumn;

    //    -------------------------专栏信息列--------------------------------
    /**
     * 是否允许投稿
     */
    @Field(index = false,type = FieldType.Boolean)
    private Boolean acceptSubmission;

    /**
     * 内容数量
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer itemsCount;

    /**
     * 专栏类型
     */
    @Field(index = false,type = FieldType.Text)
    private String columnType;

    /**
     * 专栏标题
     */
    @Field(type = FieldType.Text)
    private String columnTitle;

    /**
     * 专栏链接
     */
    @Field(index = false,type = FieldType.Text)
    private String columnUrl;


    /**
     * 作者id
     */
    @Field(index = false,type = FieldType.Text)
    private String authorId;

    /**
     * 专栏简介
     */
    @Field(type = FieldType.Text)
    private String intro;

    /**
     * 专栏图片
     */
    @Field(index = false,type = FieldType.Text)
    private String columnImageUrl;

    /**
     * 专栏关注人数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer columnFollowers;

    /**
     * 专栏文章数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer columnArticlesCount;

    /**
     * 类型
     */
    @Field(index = false,type = FieldType.Text)
    private String anotherColumnType;


    /**
     * 点赞数
     */
    @Field(index = false,type = FieldType.Integer)
    private Integer columnVoteupCount;

    private static final long serialVersionUID = 1L;

}