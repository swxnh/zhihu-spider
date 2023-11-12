package com.wenxuan.zhihuspider.service.impl;

import com.wenxuan.zhihuspider.mapper.*;
import com.wenxuan.zhihuspider.pojo.AssociationalWord;
import com.wenxuan.zhihuspider.service.AssociationalWordServer;
import com.wenxuan.zhihuspider.thread.ThreadFactoryConst;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 关联词服务实现类
 * @author 文轩
 */
@Service
public class AssociationalWordServerImpl implements AssociationalWordServer {

    private final MemberMapper memberMapper;

    private final ColumnMapper columnMapper;

    private final PaperMapper paperMapper;

    private final AssociationalWordMapper associationalWordMapper;

    private final SearchWordAssociationalWordMapper searchWordAssociationalWordMapper;


    private final StringRedisTemplate redisTemplate;

    public AssociationalWordServerImpl(MemberMapper memberMapper,
                                       ColumnMapper columnMapper,
                                       PaperMapper paperMapper,
                                       AssociationalWordMapper associationalWordMapper,
                                       SearchWordAssociationalWordMapper searchWordAssociationalWordMapper, StringRedisTemplate redisTemplate) {
        this.memberMapper = memberMapper;
        this.columnMapper = columnMapper;
        this.paperMapper = paperMapper;
        this.associationalWordMapper = associationalWordMapper;
        this.searchWordAssociationalWordMapper = searchWordAssociationalWordMapper;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 关键词初始化
     */
    @Override
    @PostConstruct
    public void init() {

        //初始化关联词表
//        initAssociationalWord();

        int size = 1000;
        int offset = 0;
        List<AssociationalWord> associationalWordList = associationalWordMapper.selectDetailByPage(offset, size);
        while (associationalWordList.size() > 0) {
            for (AssociationalWord associationalWordObj : associationalWordList) {
                if (associationalWordObj == null) {
                    continue;
                }
                String associationalWord = associationalWordObj.getAssociationalWord();
                if (associationalWord == null || associationalWord.isEmpty()) {
                    continue;
                }
                List<String> split = split(associationalWord);
                Set<String> analysis = analysis(split, 3);
                if (analysis.isEmpty()) {
                    continue;
                }
                //将关联词添加到数据库中
                searchWordAssociationalWordMapper.insertBatch(associationalWordObj.getId(),analysis);
                //将关联词添加到redis中
                for (String searchWord : analysis) {
                    //将关联词添加到redis中
                    redisTemplate.opsForZSet().add("word::" + searchWord, associationalWordObj.getId().toString(), 0);
                }
            }
            offset += size;
            associationalWordList = associationalWordMapper.selectDetailByPage(offset, size);
        }


    }

    /**
     * 联想词表初始化
     */
    @Override
    public void initAssociationalWord() {
        int size = 1000;

        //用户名作为关键词
        int memberOffset = 0;
        List<String> memberNames = memberMapper.selectNamePage(memberOffset,size);
        while (memberNames.size() > 0) {
            associationalWordMapper.insertBatch(memberNames);

            memberOffset += size;
            memberNames = memberMapper.selectNamePage(memberOffset,size);
        }

        //专栏名作为关键词
        int columnOffset = 0;
        List<String> columnNames = columnMapper.selectTitlePage(columnOffset,size);
        while (columnNames.size() > 0) {
            associationalWordMapper.insertBatch(columnNames);
            columnOffset += size;
            columnNames = columnMapper.selectTitlePage(columnOffset,size);
        }

        //文章标题作为关键词
        int paperOffset = 0;
        List<String> paperTitles = paperMapper.selectTitlePage(paperOffset,size);
        while (paperTitles.size() > 0) {
            associationalWordMapper.insertBatch(paperTitles);
            paperOffset += size;
            paperTitles = paperMapper.selectTitlePage(paperOffset,size);
        }
    }



    /**
     * 将关键词分析为关联词
     *
     * @param word
     */
    @Override
    public Set<String> analysis(String word) {
        if (word == null || word.isEmpty()) {
            return new HashSet<>(0);
        }
        //是否是纯字母
        if (word.matches("[a-zA-Z]+")) {
            return Set.of(word);
        }
        if (!filter(word)){
            return Set.of(word);
        }

        //计算出组合数,比如"abcde"的组合有"a","ab","abc","abcd","abcde","b","bc","bcd","bcde","c","cd","cde","d","de","e",共15个
        int length = word.length();
        int count = (length + 1) * length / 2;
        //获取所有组合
        Set<String> result = new HashSet<>(count);
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < charArray.length; j++) {
                sb.append(charArray[j]);
                result.add(sb.toString());
            }
        }
        return filter(result);
    }

    /**
     * 将关键词分析为关联词
     *
     * @param words
     */
    @Override
    public Set<String> analysis(List<String> words) {
        Set<String> result = new HashSet<>();
        for (String word : words) {
            result.addAll(analysis(word));
        }
        return result;
    }

    /**
     * 将关键词分析为关联词
     *
     * @param words
     * @param limit
     */
    @Override
    public Set<String> analysis(List<String> words, int limit) {
        Set<String> result = new HashSet<>();
        Set<String> analysis = analysis(words);
        for (String string : analysis) {
            int relativeLength = getRelativeLength(string);
            if (relativeLength < limit) {
                result.add(string);
            }
        }
        return result;
    }

    /**
     * 切割
     *
     * @param word
     */
    @Override
    public List<String> split(String word) {
        List<String> split = new ArrayList<>();
        //使用空格切割
        String[] splitSpace = word.split(" ");
        for (String space : splitSpace) {
            //使用标点符号切割
            String[] splitPunctuation = space.split("[\\pP\\p{Punct}]");
            //连续的字母或连续的数字或连续的汉字算一个词
            for (String punctuation : splitPunctuation) {
                char[] charArray = punctuation.toCharArray();
                StringBuilder sbLetter = new StringBuilder();
                StringBuilder sbNumber = new StringBuilder();
                StringBuilder another = new StringBuilder();
                for (int i = 0; i < charArray.length; i++) {
                    char c = charArray[i];
                    //判断是否是字母
                    if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
                        sbLetter.append(c);
                        //判断是否是最后一个字母
                        if (i == charArray.length - 1) {
                            split.add(sbLetter.toString());
                        } else {
                            //判断下一个是否是字母
                            if (!Character.isUpperCase(charArray[i + 1]) && !Character.isLowerCase(charArray[i + 1])) {
                                split.add(sbLetter.toString());
                                sbLetter = new StringBuilder();
                            }
                        }
                    } else if (Character.isDigit(c)) {
                        sbNumber.append(c);
                        //判断是否是最后一个数字
                        if (i == charArray.length - 1) {
                            split.add(sbNumber.toString());
                        } else {
                            //判断下一个是否是数字
                            if (!Character.isDigit(charArray[i + 1])) {
                                split.add(sbNumber.toString());
                                sbNumber = new StringBuilder();
                            }
                        }
                    } else {
                        another.append(c);
                        //判断是否是最后一个字符
                        if (i == charArray.length - 1) {
                            split.add(another.toString());
                        } else {
                            //判断下一个是否是字符
                            if (Character.isUpperCase(charArray[i + 1]) && Character.isLowerCase(charArray[i + 1]) && Character.isDigit(charArray[i + 1])) {
                                split.add(another.toString());
                                another = new StringBuilder();
                            }
                        }
                    }
                }
            }
        }
        return split;
    }

    /**
     * 获取相对长度
     *
     * @param word
     */
    @Override
    public int getRelativeLength(String word) {
        //连续的字母算一个长度,一个汉字算一个长度
        int length = 0;
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            //判断是否是字母
            if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
                //判断是否是最后一个字母
                if (i == charArray.length - 1) {
                    length++;
                } else {
                    //判断下一个是否是字母
                    if (!Character.isLetter(charArray[i + 1])) {
                        length++;
                    }
                }
            } else {
                length++;
            }
        }
        return length;
    }

    /**
     * 过滤关联词
     *
     * @param words
     */
    @Override
    public Set<String> filter(Set<String> words) {
        //过滤所有的标点符号和纯数字和空格
        Set<String> result = new HashSet<>(words.size());
        for (String word : words) {
            if (filter(word)){
                result.add(word);
            }
        }
        return result;
    }

    /**
     * 过滤关联词
     *
     * @param word
     */
    @Override
    public boolean filter(String word) {
        //是否为空
        if (word == null || word.isEmpty()) {
            return false;
        }
        //判断是否是纯数字
        if (word.matches("[0-9]+")) {
            //不做处理
            return false;
        }
        //判断是否完全是标点符号
        if (word.matches("[\\pP\\p{Punct}]")) {
            //不做处理
            return false;
        }
        //判断是否是空格
        if (word.matches("[\\s\\p{Blank}]")) {
            //不做处理
            return false;
        }
        return true;
    }

    /**
     * 命中
     *
     * @param searchWord
     * @param associationalWordId
     */
    @Override
    public void hit(String searchWord, Long associationalWordId) {
        //判断是否是纯数字
        if (searchWord.matches("[0-9]+")) {
            //不做处理
            return;
        }
        //判断是否完全是标点符号
        if (searchWord.matches("[\\pP\\p{Punct}]")) {
            //不做处理
            return;
        }
        //判断是否是空格
        if (searchWord.matches("[\\s\\p{Blank}]")) {
            //不做处理
            return;
        }
        //更新关联词的分数
        ThreadFactoryConst.IO_THREAD_FACTORY.newThread(()->{
            redisTemplate.opsForZSet().incrementScore("word::" + searchWord, associationalWordId.toString(), 1);
            searchWordAssociationalWordMapper.plusScore(searchWord, associationalWordId);
        }).start();

    }


}
