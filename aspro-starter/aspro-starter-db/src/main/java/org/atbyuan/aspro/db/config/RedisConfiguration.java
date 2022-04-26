package org.atbyuan.aspro.db.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author atbyuan
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

    // public JedisConnectionFactory jedisConnectionFactory(int database, String hostName, int port, String password) {
    //     RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    //     redisStandaloneConfiguration.setHostName(hostName);
    //     redisStandaloneConfiguration.setPort(port);
    //     redisStandaloneConfiguration.setDatabase(database);
    //     redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
    //     JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
    //     // 60s connection timeout
    //     jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
    //     return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    // }

}
