package org.atbyuan.aspro.service.engine.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.atbyuan.aspro.common.enums.AsproEnums.SystemCode;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.atbyuan.aspro.common.response.ApiResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.message.AuthException;
import javax.servlet.ServletException;
        
/**
 * @author atbyuan
 * @since 2022/4/19 12:31
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse<?> handleException(Exception ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            return ApiResponse.<String>builder().code(businessException.getCode())
                    .message(businessException.getMessage()).data(className).build();
        }
        // else if (ex instanceof FeignRemoteException) {
        //     FeignRemoteException feignRemoteException = (FeignRemoteException) ex;
        //     return ApiResponse.builder().code(feignRemoteException.status())
        //             .message(feignRemoteException.getMessage()).data(className).build();
        // }
        return ApiResponse.builder().code(SystemCode.SYSTEM_ERROR.getCode())
                .message(SystemCode.SYSTEM_ERROR.getMessage()).data(className).build();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ApiResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return ApiResponse.builder().code(SystemCode.SYSTEM_REQUEST_PARAM_NO_FOUND.getCode())
                .message(SystemCode.SYSTEM_REQUEST_PARAM_NO_FOUND.getMessage()).data(className).build();
    }

    @ExceptionHandler(value = AuthException.class)
    public ApiResponse<?> handleAuthException(AuthException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return ApiResponse.builder().code(SystemCode.SYSTEM_NO_AUTH.getCode())
                .message(SystemCode.SYSTEM_NO_AUTH.getMessage()).data(className).build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ApiResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return ApiResponse.builder().code(SystemCode.SYSTEM_METHOD_ERROR.getCode())
                .message(SystemCode.SYSTEM_METHOD_ERROR.getMessage()).data(className).build();
    }

    @ExceptionHandler(value = ServletException.class)
    public ApiResponse<?> handleServletException(ServletException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex instanceof NoHandlerFoundException) {
            log.error("未找到请求路径：{}", ((NoHandlerFoundException) ex).getRequestURL());
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_FOUND.getCode())
                    .message(SystemCode.SYSTEM_NO_FOUND.getMessage()).data(className).build();
        }
        return ApiResponse.builder().code(SystemCode.SYSTEM_ERROR.getCode())
                .message(SystemCode.SYSTEM_ERROR.getMessage()).data(className).build();
    }

    @ExceptionHandler(value = BindException.class)
    public ApiResponse<?> handleBindException(BindException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex.getGlobalError() != null) {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(ex.getGlobalError().getDefaultMessage()).data(className).build();

        } else if (ex.getFieldError() != null) {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(ex.getFieldError().getDefaultMessage()).data(className).build();

        } else {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(SystemCode.SYSTEM_NO_VALID.getMessage()).data(className).build();
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.getGlobalError() != null) {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(bindingResult.getGlobalError().getDefaultMessage()).data(className).build();

        } else if (bindingResult.getFieldError() != null) {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(bindingResult.getFieldError().getDefaultMessage()).data(className).build();

        } else {
            return ApiResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(SystemCode.SYSTEM_NO_VALID.getMessage()).data(className).build();
        }
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return ApiResponse.builder().code(SystemCode.SYSTEM_ERROR.getCode())
                .message(ex.getMessage()).data(className).build();
    }

    // @ExceptionHandler(value = ClientException.class)
    // public ApiResponse<?> handleClientException(ClientException ex) {
    //     String className = ex.getClass().getName();
    //     log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
    //     return ApiResponse.builder().code(SystemCode.SYSTEM_SERVER_ERROR.getCode())
    //             .message(SystemCode.SYSTEM_SERVER_ERROR.getMessage()).data(className).build();
    // }

    // @ExceptionHandler(value = FeignException.class)
    // public ApiResponse<?> handleFeignException(FeignException ex) {
    //     String className = ex.getClass().getName();
    //     log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
    //     String message = ex.getMessage();
    //     if (StrUtil.isNotEmpty(message) && ReUtil.contains("[\\u4e00-\\u9fa5]", message)) {
    //         return ApiResponse.builder().code(ex.status()).message(message).data(className).build();
    //
    //     } else {
    //         return ApiResponse.builder().code(SystemCode.SYSTEM_SERVER_ERROR.getCode())
    //                 .message(SystemCode.SYSTEM_SERVER_ERROR.getMessage()).data(className).build();
    //     }
    // }

}
