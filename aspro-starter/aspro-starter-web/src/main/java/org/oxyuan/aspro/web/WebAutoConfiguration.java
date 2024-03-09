package org.oxyuan.aspro.web;

import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.web.advice.ExceptionHandlerAdvice;
import org.oxyuan.aspro.web.webmvc.WebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author oxyuan
 * @since 2022/4/19 23:06
 */
@Slf4j
@Configuration
@Import({WebMvcConfiguration.class, ExceptionHandlerAdvice.class})
public class WebAutoConfiguration {

}
