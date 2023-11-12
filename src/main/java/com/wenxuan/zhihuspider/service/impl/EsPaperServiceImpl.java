package com.wenxuan.zhihuspider.service.impl;

import com.wenxuan.zhihuspider.mapper.PaperMapper;
import com.wenxuan.zhihuspider.pojo.EsPaper;
import com.wenxuan.zhihuspider.repository.EsPaperRepository;
import com.wenxuan.zhihuspider.service.EsPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 文轩
 */
@Service
@Slf4j
public class EsPaperServiceImpl implements EsPaperService {

    private final PaperMapper paperMapper;

    private final EsPaperRepository esPaperRepository;


    public EsPaperServiceImpl(PaperMapper paperMapper,
                              EsPaperRepository esPaperRepository) {
        this.paperMapper = paperMapper;
        this.esPaperRepository = esPaperRepository;
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
        int start = 0;
        int size = 1000;
        while (true) {

            List<EsPaper> esPapers = paperMapper.selectAllDetails(start, size);
            if (esPapers.isEmpty()) {
                break;
            }
            esPaperRepository.saveAll(esPapers);
            log.info("存入es成功");
            start += size;
        }

    }


    /**
     * 同步数据
     */
    @Override
    @CacheEvict(value = {"searchContent", "searchTitle", "searchExcerpt", "searchAll"}, allEntries = true)
    public void syncData() {
        log.info("开始同步数据");
        //获取es最后创建的数据
        EsPaper esPaper = esPaperRepository.findTopByOrderByCreateTimeDesc();
        Date lastCreateTime = esPaper.getCreateTime();
        log.info("es最后创建的数据时间：{}", lastCreateTime);
        List<EsPaper> esPapers;
        //查询mysql中大于最后创建时间的数据
        esPapers = paperMapper.selectBeforCreateTime(lastCreateTime);
        log.info("查询到数据：{}条", esPapers.size());
        if (esPapers.isEmpty()) {
            log.info("没有数据需要同步");
        }else {
            //存入es
            esPaperRepository.saveAll(esPapers);
            log.info("存入es成功");
        }

        //获取es最后更新的数据
        EsPaper all = esPaperRepository.findTopByOrderByUpdateTimeDesc();
        Date lastUpdateTime = all.getUpdateTime();
        log.info("es最后更新的数据时间：{}", lastUpdateTime);
        //查询mysql中大于最后更新时间的数据
        esPapers = paperMapper.selectByUpdateTime(lastUpdateTime);
        log.info("查询到数据：{}条", esPapers.size());
        if (esPapers.isEmpty()) {
            log.info("没有数据需要同步");
            return;
        }
        //存入es
        esPaperRepository.saveAll(esPapers);
        log.info("存入es成功");
    }

}
