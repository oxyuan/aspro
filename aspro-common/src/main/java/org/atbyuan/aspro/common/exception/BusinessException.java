package org.atbyuan.aspro.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author atbyuan
 * @since 2022/4/18 12:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private int code;
    private String message;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code) {
        this.code = code;
    }
}
