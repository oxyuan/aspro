package org.atbyuan.aspro.web;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.web.advice.ExceptionHandlerAdvice;
import org.atbyuan.aspro.web.webmvc.WebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author atbyuan
 * @since 2022/4/19 23:06
 */
@Slf4j
@Configuration
@Import({WebMvcConfiguration.class, ExceptionHandlerAdvice.class})
public class WebAutoConfiguration {

}
