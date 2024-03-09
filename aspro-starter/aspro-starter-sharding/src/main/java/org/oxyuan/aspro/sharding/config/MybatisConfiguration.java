package org.oxyuan.aspro.sharding.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author oxyuan
 * @since 2022/4/19 12:42
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "org.oxyuan.aspro.sharding.mapper")
public class MybatisConfiguration {

}
