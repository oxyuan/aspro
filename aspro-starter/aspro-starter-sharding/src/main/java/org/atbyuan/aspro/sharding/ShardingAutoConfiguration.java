package org.atbyuan.aspro.sharding;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.sharding.config.MybatisConfiguration;
import org.atbyuan.aspro.sharding.config.ShardingConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author atbyuan
 * @since 2022/9/28 9:41
 */
@Slf4j
@Configuration
@Import({ShardingConfiguration.class, MybatisConfiguration.class})
public class ShardingAutoConfiguration {

}