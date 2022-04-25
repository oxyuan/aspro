package org.atbyuan.aspro.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author atbyuan
 * @since 2022/4/18 12:46
 */
public interface AsproEnums {

    @Getter
    @AllArgsConstructor
    enum SystemCode {

        // 系统响应失败
        SYSTEM_FAIL(-1, "fail"),
        // 系统响应成功
        SYSTEM_SUCCESS(0, "success"),
        // 参数验证错误
        SYSTEM_NO_VALID(400, "参数验证错误！"),
        // 无权访问
        SYSTEM_NO_AUTH(401, "无权操作！"),
        // 拒绝访问
        SYSTEM_BAD_REQUEST(403, "请求频率过快,请稍后再试！"),
        // 资源未找到
        SYSTEM_NO_FOUND(404, "资源未找到！"),
        // 请求方式错误
        SYSTEM_METHOD_ERROR(405, "请求方式错误！"),
        // 请求参数缺失
        SYSTEM_REQUEST_PARAM_NO_FOUND(406, "请求参数缺失！"),
        // 请求超时
        SYSTEM_REQUEST_TIMEOUT(408, "请求超时！"),
        // 服务调用异常
        SYSTEM_SERVER_ERROR(410, "服务调用异常！"),
        // 未捕获的错误
        SYSTEM_ERROR(500, "系统异常，请联系管理员！");

        private final int code;
        private final String message;

        public static SystemCode of(int code) {
            for (SystemCode codeEnum : values()) {
                if (codeEnum.getCode() == code) {
                    return codeEnum;
                }
            }
            return SystemCode.SYSTEM_ERROR;
        }
    }


    @Getter
    @AllArgsConstructor
    enum Gateway {
        ADMIN(1, "admin"),
        API(2, "api"),
        ENGINE(3, "engine"),
        ;

        private final Integer code;
        private final String desc;

        public static Gateway of(int code) {
            for (Gateway codeEnum : values()) {
                if (codeEnum.getCode() == code) {
                    return codeEnum;
                }
            }
            return null;
        }
    }
}
