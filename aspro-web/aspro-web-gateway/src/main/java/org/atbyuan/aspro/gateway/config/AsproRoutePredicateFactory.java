package org.atbyuan.aspro.gateway.config;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atbyuan.aspro.common.enums.AsproEnums;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.atbyuan.aspro.common.enums.AsproEnums.SystemCode.SYSTEM_SERVER_ERROR;

/**
 * @author atbyuan
 * @since 2022/4/22 12:32
 */
@Slf4j
@Component
public class AsproRoutePredicateFactory extends AbstractRoutePredicateFactory<AsproRoutePredicateFactory.Config> {

    private static final String ROUTE_HEADER_TOKEN = "route-token";

    public AsproRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(AsproRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
            String routeHeaderToken = headers.getFirst(ROUTE_HEADER_TOKEN);
            if (StringUtils.isBlank(routeHeaderToken)
                    ||
                    !NumberUtil.isNumber(routeHeaderToken)
                    ||
                    AsproEnums.Gateway.of(Integer.parseInt(routeHeaderToken)) == null) {
                throw new BusinessException(SYSTEM_SERVER_ERROR);
            }
            return config.getToken().equals(Integer.parseInt(routeHeaderToken));
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("token");
    }

    @Data
    @Validated
    public static class Config {
        @NotNull
        private Integer token;
    }
}
