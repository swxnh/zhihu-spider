package com.wenxuan.zhihuspider.spider;

import com.wenxuan.zhihuspider.mapper.MemberMapper;
import com.wenxuan.zhihuspider.pojo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 文轩
 */
@Component
@Slf4j
public class FollowersSpiderAction {


    private final FollowersSpider followersSpider;

    private final MemberMapper memberMapper;


    public FollowersSpiderAction(FollowersSpider followersSpider,
                                 MemberMapper memberMapper) {
        this.followersSpider = followersSpider;
        this.memberMapper = memberMapper;
    }

    public void action(Integer count) {
        log.info("开始爬取知乎用户的关注者");
        //爬取爬取次数小于count的用户
        List<Member> memberList = memberMapper.selectSpiderCountLessThan(count,10);
        for (Member member : memberList) {
            if (member.getUrlToken() == null) {
                continue;
            }
            spider(member.getUrlToken());
            //根据id增加爬取次数
            log.info("爬取成功，准备更新爬取次数: urlToken={}", member.getUrlToken());
            memberMapper.plusSpiderCountById(member.getId());
        }
    }

    public void spider(String urlToken) {
        log.info("开始爬取知乎用户的关注者，urlToken={}", urlToken);
        followersSpider.spiderAll(urlToken);
        log.info("爬取知乎用户的关注者结束，urlToken={}", urlToken);
    }
}
