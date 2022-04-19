package org.atbyuan.aspro.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atbyuan.aspro.common.enums.AsproEnums;


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

    public BusinessException(String message) {
        this.code = AsproEnums.SystemCode.SYSTEM_ERROR.getCode();
        this.message = message;
    }
}
