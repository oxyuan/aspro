package org.atbyuan.aspro.gateway.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.enums.AsproEnums;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.atbyuan.aspro.common.response.ApiResponse;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author atbyuan
 * @since 2022/4/18 23:25
 */
@Slf4j
@Component
@Order(-2)
public class WebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public WebExceptionHandler(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
                               ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new ResourceProperties(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        log.error("网关请求错误：{}", JSON.toJSONString(errorAttributes));

        Throwable throwable = getError(request);
        if (throwable instanceof BusinessException) {
            BusinessException commonException = (BusinessException) throwable;
            ApiResponse<?> resObject = ApiResponse.builder()
                    .code(commonException.getCode())
                    .message(commonException.getMessage())
                    .build();
            BodyInserter<ApiResponse<?>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromValue(resObject);
            return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(bodyInserter);

        }
        // else if (throwable instanceof FeignRemoteException) {
        //     FeignRemoteException feignRemoteException = (FeignRemoteException) throwable;
        //     ResObject<?> resObject = ResObject.builder()
        //             .code(feignRemoteException.status())
        //             .message(feignRemoteException.getMessage())
        //             .build();
        //     BodyInserter<ResObject<?>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromValue(resObject);
        //     return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(bodyInserter);
        //
        // }
        else {
            Integer status = (Integer) errorAttributes.get("status");
            AsproEnums.SystemCode systemCodeEnum = AsproEnums.SystemCode.of(status);
            ApiResponse<?> resObject = ApiResponse.builder()
                    .code(systemCodeEnum.getCode())
                    .message(systemCodeEnum.getMessage())
                    .build();
            BodyInserter<ApiResponse<?>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromValue(resObject);
            return ServerResponse.status(HttpStatus.valueOf(status)).contentType(MediaType.APPLICATION_JSON).body(bodyInserter);
        }
    }
}
