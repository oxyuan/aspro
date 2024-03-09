package org.oxyuan.aspro.redis.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author oxyuan
 * @since 2022/10/05 12:56
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * 限流唯一标示
     */
    String key() default "";

    /**
     * 限流时间
     */
    int time();

    /**
     * 限流次数
     */
    int count();

}
