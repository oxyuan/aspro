package org.atbyuan.aspro.config;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.config.advice.ExceptionHandlerAdvice;
import org.atbyuan.aspro.config.webmvc.WebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author atbyuan
 * @since 2022/4/19 23:06
 */
@Slf4j
@Configuration
@Import({WebMvcConfiguration.class, ExceptionHandlerAdvice.class})
public class ConfAutoConfiguration {

}
