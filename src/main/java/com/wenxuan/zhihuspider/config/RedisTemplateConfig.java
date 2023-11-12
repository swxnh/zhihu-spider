//package com.wenxuan.zhihuspider.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * RedisTemplate配置类
// * @author 文轩
// */
//@Configuration
//public class RedisTemplateConfig {
//
//    /**
//     * 注入RedisTemplate<String, Long>
//     */
//     @Bean
//        public RedisTemplate<String, Long> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//            RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
//            redisTemplate.setConnectionFactory(redisConnectionFactory);
//            redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//            return redisTemplate;
//        }
//}
