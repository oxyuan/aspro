package org.oxyuan.aspro.web.webmvc;

import cn.hutool.core.date.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author oxyuan
 * @since 2022/4/19 23:02
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(@NonNull String param) {
                return Optional.of(param).map((Function<String, Date>) DateUtil::parse).orElse(null);
            }
        });
    }

}
