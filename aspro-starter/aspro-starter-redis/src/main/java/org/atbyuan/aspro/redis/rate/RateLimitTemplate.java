package org.atbyuan.aspro.redis.rate;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.redis.annotation.RateLimit;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author atbyuan
 * @since 2022-10-05 15:02
 */
@Slf4j
@Component
public class RateLimitTemplate {

    @Resource
    private StringRedisTemplate limitRedisTemplate;
    @Resource
    private DefaultRedisScript<Number> redisLuaScript;

    public Number execute(List<String> keys, RateLimit rateLimit) {
        return limitRedisTemplate.execute(redisLuaScript, keys, rateLimit.count(), rateLimit.time());
    }

}
