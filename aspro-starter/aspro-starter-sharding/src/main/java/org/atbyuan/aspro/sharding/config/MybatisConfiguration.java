package org.atbyuan.aspro.sharding.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author atbyuan
 * @since 2022/4/19 12:42
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "org.atbyuan.aspro.sharding.mapper")
public class MybatisConfiguration {

}
