package org.atbyuan.aspro.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.atbyuan.aspro.redis.annotation.RateLimit;
import org.atbyuan.aspro.redis.rate.RateLimitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author atbyuan
 * @since 2022-10-05 14:12
 */
@Slf4j
@Aspect
@Configuration
public class RateLimitAspect {

    private static final String RATE_LIMIT_KEY = "%s-%s-%s-%s";
    @Resource
    private RateLimitTemplate rateLimitTemplate;

    @Around("execution(* org.atbyuan.aspro.admin.controller ..*(..) )")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        // 无须限流
        if (rateLimit == null) {
            return joinPoint.proceed();
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ipAddress = getIpAddr(request);
        String stringBuffer = String.format(RATE_LIMIT_KEY, ipAddress, targetClass.getName(), method.getName(), rateLimit.key());
        List<String> keys = Collections.singletonList(stringBuffer);

        Number number = rateLimitTemplate.execute(keys, rateLimit);
        if (number != null && number.intValue() != 0 && number.intValue() <= rateLimit.count()) {
            log.info("限流时间段内访问第：{} 次", number);
            return joinPoint.proceed();
        }
        throw new BusinessException("已经到设置限流次数");
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}
