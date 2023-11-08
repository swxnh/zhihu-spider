package com.wenxuan.zhihuspider.converter;

import com.wenxuan.zhihuspider.pojo.Column;
import com.wenxuan.zhihuspider.pojo.Member;
import com.wenxuan.zhihuspider.pojo.Paper;
import com.wenxuan.zhihuspider.spider.pojo.columns.Author;
import com.wenxuan.zhihuspider.spider.pojo.columns.ColumnBO;
import com.wenxuan.zhihuspider.spider.pojo.papers.*;
import com.wenxuan.zhihuspider.spider.pojo.zhihuuser.FollowerData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 知乎转换器
 * @author 文轩
 */
@Component
public class ZhiHuConverter {

    public Member toMember(FollowerData followerData) {
        Member member = new Member();
        member.setZhihuMemberId(followerData.getId());
        member.setGender(followerData.getGender());
        member.setAnswerCount(followerData.getAnswer_count());
        member.setAvatarUrl(followerData.getAvatar_url());
        member.setHeadline(followerData.getHeadline());
        member.setArticlesCount(followerData.getArticles_count());
        member.setName(followerData.getName());
        member.setType(followerData.getType());
        member.setUrl(followerData.getUrl());
        member.setFollowerCount(followerData.getFollower_count());
        member.setIpInfo(followerData.getIp_info());
        member.setIsOrg(followerData.is_org());
        member.setUrlToken(followerData.getUrl_token());
        member.setAvatarUrlTemplate(followerData.getAvatar_url_template());
        member.setUseDefaultAvatar(followerData.isUse_default_avatar());
        member.setUserType(followerData.getUser_type());
        member.setIsAdvertiser(followerData.is_advertiser());
        member.setHeadlineRender(followerData.getHeadline_render());
        member.setIsRealname(followerData.is_realname());
        member.setHasApplyingColumn(followerData.isHas_applying_column());
        member.setIpInfo(followerData.getIp_info());
        return member;

    }


    public List<Member> toMember(List<FollowerData> followerDataList) {
        List<Member> memberList = new ArrayList<>(followerDataList.size());
        for (FollowerData followerData : followerDataList) {
            Member member = toMember(followerData);
            memberList.add(member);
        }
        return memberList;
    }

    public Column toColumn(ColumnBO columnBO) {
        Column column = new Column();
        column.setAcceptSubmission(columnBO.getAccept_submission());
        column.setCommentPermission(columnBO.getComment_permission());
        column.setColumnType(columnBO.getColumn_type());
        column.setIntro(columnBO.getIntro());
        column.setItemsCount(columnBO.getItems_count());
        column.setTitle(columnBO.getTitle());
        column.setUpdated(new Date(columnBO.getUpdated()));
        column.setUrl(columnBO.getUrl());
        column.setFollowers(columnBO.getFollowers());
        column.setImageUrl(columnBO.getImage_url());
        Author author = columnBO.getAuthor();
        column.setAuthorId(author.getId());
        column.setZhihuColumnId(columnBO.getId());
        column.setArticlesCount(columnBO.getArticles_count());
        column.setVoteupCount(columnBO.getVoteup_count());
        column.setType(columnBO.getType());
        return column;
    }

    public List<Column> toColumn(List<ColumnBO> columnBOList) {
        List<Column> columnList = new ArrayList<>(columnBOList.size());
        for (ColumnBO columnBO : columnBOList) {
            Column column = toColumn(columnBO);
            columnList.add(column);
        }
        return columnList;
    }

    public Paper toPaper(PaperBO paperBO) {
        Paper paper = new Paper();
        paper.setUpdated(paperBO.getUpdated());
        paper.setIsLabeled(paperBO.getIs_labeled());
        paper.setCopyrightPermission(paperBO.getCopyright_permission());
        paper.setExcerpt(paperBO.getExcerpt());
        paper.setAdminClosedComment(paperBO.getAdmin_closed_comment());
        paper.setVoting(paperBO.getVoting());
        paper.setArticleType(paperBO.getArticle_type());
        paper.setReason(paperBO.getReason());
        paper.setExcerptTitle(paperBO.getExcerpt_title());
        paper.setVoteupCount(paperBO.getVoteup_count());
        paper.setTitleImage(paperBO.getTitle_image());
        paper.setHasColumn(paperBO.getHas_column());
        paper.setUrl(paperBO.getUrl());
        paper.setCommentPermission(paperBO.getComment_permission());
        com.wenxuan.zhihuspider.spider.pojo.papers.Author author = paperBO.getAuthor();
        if (author != null) {
            paper.setZhihuMemberId(author.getId());
        }
        paper.setCommentCount(paperBO.getComment_count());
        paper.setCreated(paperBO.getCreated());
        paper.setContent(paperBO.getContent());
        paper.setState(paperBO.getState());
        paper.setImageUrl(paperBO.getImage_url());
        paper.setTitle(paperBO.getTitle());
        paper.setType(paperBO.getType());
        SuggestEdit suggestEdit = paperBO.getSuggest_edit();
        if (suggestEdit != null) {
            paper.setSuggestEditStatus(suggestEdit.getStatus());
            paper.setSuggestEditReason(suggestEdit.getReason());
            paper.setSuggestEditUrl(suggestEdit.getUrl());
            paper.setSuggestEditTip(suggestEdit.getTip());
            paper.setSuggestEditTitle(suggestEdit.getTitle());
        }
        CanComment canComment = paperBO.getCan_comment();
        if (canComment != null) {
            paper.setCanCommentReason(canComment.getReason());
            paper.setCanComment(canComment.getStatus());
        }
        paper.setZhihuPaperId(paperBO.getId());
        Settings settings = paperBO.getSettings();
        if (settings != null) {
            TableOfContents tableOfContents = settings.getTable_of_contents();
            if (tableOfContents != null) {
                paper.setTableOfContentsEnable(tableOfContents.getEnabled());
            }
        }


        return paper;
    }

    public List<Paper> toPaper(List<PaperBO> paperBOList) {
        List<Paper> paperList = new ArrayList<>(paperBOList.size());
        for (PaperBO paperBO : paperBOList) {
            Paper paper = toPaper(paperBO);
            paperList.add(paper);
        }
        return paperList;
    }
}
