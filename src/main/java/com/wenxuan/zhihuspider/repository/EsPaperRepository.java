package com.wenxuan.zhihuspider.repository;

import com.wenxuan.zhihuspider.pojo.EsPaper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 文轩
 */
public interface EsPaperRepository extends ElasticsearchRepository<EsPaper, Long> {


    EsPaper findTopByOrderByUpdateTimeDesc();

    EsPaper findTopByOrderByCreateTimeDesc();
}
