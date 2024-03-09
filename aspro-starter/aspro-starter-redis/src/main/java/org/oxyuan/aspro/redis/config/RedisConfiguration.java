package org.oxyuan.aspro.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;

/**
 * @author oxyuan
 * @since 2022/4/25 22:56
 */
@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // value值的序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // key的序列化
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

        Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(String.class);
        // value值的序列化
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // key的序列化
        stringRedisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

//    @Bean(destroyMethod = "shutdown")
//    @ConditionalOnMissingBean(RedissonClient.class)
//    public RedissonClient redisson() throws IOException {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
//                .setPassword(redisProperties.getPassword())
//                .setDatabase(redisProperties.getDatabase())
//                .setConnectionMinimumIdleSize(5);
//        return Redisson.create(config);
//    }

    /**
     * 读取限流脚本
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Number> redisLuaScript() {
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimit.lua")));
        redisScript.setResultType(Number.class);
        return redisScript;
    }

}
