package org.oxyuan.aspro.redis;


import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.redis.config.CacheConfiguration;
import org.oxyuan.aspro.redis.config.RedisConfiguration;
import org.oxyuan.aspro.redis.rate.RateLimitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author oxyuan
 * @since 2022/9/28 09:26
 */
@Slf4j
@Configuration
@Import({
        RedisConfiguration.class,
        CacheConfiguration.class,
        RateLimitTemplate.class,
})
public class RedisAutoConfiguration {

}