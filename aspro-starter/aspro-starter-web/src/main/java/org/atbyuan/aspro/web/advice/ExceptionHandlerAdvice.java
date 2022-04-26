package org.atbyuan.aspro.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.atbyuan.aspro.common.enums.AsproEnums.SystemCode;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.atbyuan.aspro.common.response.AsproResponse;
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
    public AsproResponse<?> handleException(Exception ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            return AsproResponse.<String>builder().code(businessException.getCode())
                    .message(businessException.getMessage()).data(className).build();
        }
        // else if (ex instanceof FeignRemoteException) {
        //     FeignRemoteException feignRemoteException = (FeignRemoteException) ex;
        //     return AsproResponse.builder().code(feignRemoteException.status())
        //             .message(feignRemoteException.getMessage()).data(className).build();
        // }
        return AsproResponse.builder()
                .code(SystemCode.SYSTEM_ERROR.getCode())
                .message(SystemCode.SYSTEM_ERROR.getMessage())
                .data(className)
                .build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public AsproResponse<?> handleBusinessException(BusinessException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return AsproResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public AsproResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return AsproResponse.builder()
                .code(SystemCode.SYSTEM_REQUEST_PARAM_NO_FOUND.getCode())
                .message(SystemCode.SYSTEM_REQUEST_PARAM_NO_FOUND.getMessage())
                .data(className)
                .build();
    }

    @ExceptionHandler(value = AuthException.class)
    public AsproResponse<?> handleAuthException(AuthException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return AsproResponse.builder()
                .code(SystemCode.SYSTEM_NO_AUTH.getCode())
                .message(SystemCode.SYSTEM_NO_AUTH.getMessage())
                .data(className)
                .build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public AsproResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return AsproResponse.builder()
                .code(SystemCode.SYSTEM_METHOD_ERROR.getCode())
                .message(SystemCode.SYSTEM_METHOD_ERROR.getMessage())
                .data(className)
                .build();
    }

    @ExceptionHandler(value = ServletException.class)
    public AsproResponse<?> handleServletException(ServletException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex instanceof NoHandlerFoundException) {
            log.error("未找到请求路径：{}", ((NoHandlerFoundException) ex).getRequestURL());
            return AsproResponse.builder()
                    .code(SystemCode.SYSTEM_NO_FOUND.getCode())
                    .message(SystemCode.SYSTEM_NO_FOUND.getMessage())
                    .data(className)
                    .build();
        }
        return AsproResponse.builder()
                .code(SystemCode.SYSTEM_ERROR.getCode())
                .message(SystemCode.SYSTEM_ERROR.getMessage())
                .data(className)
                .build();
    }

    @ExceptionHandler(value = BindException.class)
    public AsproResponse<?> handleBindException(BindException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        if (ex.getGlobalError() != null) {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(ex.getGlobalError().getDefaultMessage()).data(className).build();

        } else if (ex.getFieldError() != null) {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(ex.getFieldError().getDefaultMessage()).data(className).build();

        } else {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(SystemCode.SYSTEM_NO_VALID.getMessage()).data(className).build();
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AsproResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.getGlobalError() != null) {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(bindingResult.getGlobalError().getDefaultMessage()).data(className).build();

        } else if (bindingResult.getFieldError() != null) {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(bindingResult.getFieldError().getDefaultMessage()).data(className).build();

        } else {
            return AsproResponse.builder().code(SystemCode.SYSTEM_NO_VALID.getCode())
                    .message(SystemCode.SYSTEM_NO_VALID.getMessage()).data(className).build();
        }
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public AsproResponse<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        String className = ex.getClass().getName();
        log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
        return AsproResponse.builder().code(SystemCode.SYSTEM_ERROR.getCode())
                .message(ex.getMessage()).data(className).build();
    }

    // @ExceptionHandler(value = ClientException.class)
    // public AsproResponse<?> handleClientException(ClientException ex) {
    //     String className = ex.getClass().getName();
    //     log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
    //     return AsproResponse.builder().code(SystemCode.SYSTEM_SERVER_ERROR.getCode())
    //             .message(SystemCode.SYSTEM_SERVER_ERROR.getMessage()).data(className).build();
    // }

    // @ExceptionHandler(value = FeignException.class)
    // public AsproResponse<?> handleFeignException(FeignException ex) {
    //     String className = ex.getClass().getName();
    //     log.error("Exception occurred! ex: {}, stack:{}", className, ExceptionUtils.getStackTrace(ex));
    //     String message = ex.getMessage();
    //     if (StrUtil.isNotEmpty(message) && ReUtil.contains("[\\u4e00-\\u9fa5]", message)) {
    //         return AsproResponse.builder().code(ex.status()).message(message).data(className).build();
    //
    //     } else {
    //         return AsproResponse.builder().code(SystemCode.SYSTEM_SERVER_ERROR.getCode())
    //                 .message(SystemCode.SYSTEM_SERVER_ERROR.getMessage()).data(className).build();
    //     }
    // }

}
