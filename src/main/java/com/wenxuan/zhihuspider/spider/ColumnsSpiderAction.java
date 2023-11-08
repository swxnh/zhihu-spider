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
public class ColumnsSpiderAction {

    private final ColumnsSpider columnsSpider;

    private final MemberMapper memberMapper;

    public ColumnsSpiderAction(ColumnsSpider columnsSpider,
                               MemberMapper memberMapper) {
        this.columnsSpider = columnsSpider;
        this.memberMapper = memberMapper;
    }

    public void action(Integer count) {
        log.info("开始爬取专栏");
        //爬取爬取次数在count和count-1之间的用户
        List<Member> memberList = memberMapper.selectSpiderCountBetween(count - 1, count, 100);
        for (Member member : memberList) {
            String urlToken = member.getUrlToken();
            spider(urlToken);
            log.info("爬取专栏结束，准备更新爬取次数: urlToken={}", urlToken);
            memberMapper.plusSpiderCountById(member.getId());
        }
    }

    public void spider(String urlToken) {
        log.info("开始爬取专栏: urlToken={}", urlToken);
        columnsSpider.spiderAll(urlToken);
        log.info("爬取专栏结束: urlToken={}", urlToken);
    }

}
