package org.oxyuan.aspro.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.oxyuan.aspro.redis.annotation.RateLimit;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author oxyuan
 * @since 2022-10-05 15:14
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 10 秒中，可以访问10次
    @RateLimit(key = "rate_limit", time = 10, count = 10)
    @GetMapping("/rate_limit")
    public AsproResponse<String> luaLimiter() {
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger("entityIdCounter", stringRedisTemplate.getConnectionFactory());
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        return AsproResponse.success(date + ". 累计访问次数：" + entityIdCounter.getAndIncrement());
    }

}
