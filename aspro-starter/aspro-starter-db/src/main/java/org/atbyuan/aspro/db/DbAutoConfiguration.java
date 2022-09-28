package org.atbyuan.aspro.db;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.db.config.MybatisConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author atbyuan
 * @since 2022/4/19 12:46
 */
@Slf4j
@Configuration
@Import({MybatisConfiguration.class})
public class DbAutoConfiguration {

}
