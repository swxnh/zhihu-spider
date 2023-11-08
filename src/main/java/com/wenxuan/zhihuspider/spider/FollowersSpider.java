package com.wenxuan.zhihuspider.spider;


import com.wenxuan.zhihuspider.converter.ZhiHuConverter;
import com.wenxuan.zhihuspider.mapper.MemberMapper;
import com.wenxuan.zhihuspider.pojo.Member;
import com.wenxuan.zhihuspider.spider.pojo.zhihuuser.FollowerData;
import com.wenxuan.zhihuspider.spider.pojo.zhihuuser.FollowersPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 文轩
 */
@Component
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class FollowersSpider extends BaseSpider<FollowersPage> {

    private final static String URL = "";

    private final static String DEFAULT_PARAMS = "/followees?include=data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics";

    private final String startLogTemplate = "开始爬取知乎用户: url={}";

    private final String failLogTemplate = "爬取知乎用户失败: url={}, statusCode={}";

    private final String errorLogTemplate = "爬取知乎用户异常: url={}";

    private final Class<FollowersPage> clazz = FollowersPage.class;



    private final Map<String, String> headers;

    private final ZhiHuConverter zhiHuConverter;

    private final MemberMapper memberMapper;

    public FollowersSpider(ZhiHuConverter zhiHuConverter,
                           MemberMapper memberMapper) {
        this.zhiHuConverter = zhiHuConverter;
        this.memberMapper = memberMapper;
        this.headers = new HashMap<>();
        this.headers.put("Cookie", "");
        this.headers.put("x-zse-93", "101_3_3.0");
        this.headers.put("x-zse-96", "2.0_");
        this.headers.put("x-app-za", "OS=Web");
    }


    /**
     * 组装爬虫前缀
     *
     * @param params
     * @param offset
     * @param limit
     */
    @Override
    public String getUrl(String params, int offset, int limit) {
        return "https://www.zhihu.com/api/v4/members/"+params+"/followees?include=data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics"+"&offset="+offset+"&limit="+limit;
    }

    @Override
    public void handlerPage(FollowersPage page, String params) {
        log.info("爬取关注者开始: urlToken={}", params);
        //获取关注者列表
        List<FollowerData> followerDataList = page.getData();
        for (FollowerData followerData : followerDataList) {
            //转换成Member
            Member member = zhiHuConverter.toMember(followerData);
            handlerData(member);
        }
    }

    private void handlerData(Member member) {
        int exist = memberMapper.existByZhihuMemberId(member.getZhihuMemberId());
        if (exist == 0) {
            log.info("插入用户， name={} urlToken={}", member.getName(), member.getUrlToken());
            memberMapper.insert(member);
        }
        else {
            log.info("更新用户，name={} urlToken={}", member.getName(), member.getUrlToken());
            memberMapper.updateByZhihuMemberId(member);
        }
    }

}
