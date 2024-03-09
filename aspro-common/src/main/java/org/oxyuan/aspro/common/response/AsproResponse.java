package org.oxyuan.aspro.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oxyuan.aspro.common.enums.AsproEnums;

/**
 * @author oxyuan
 * @since 2022/4/18 18:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsproResponse<T> {

    private Integer code;
    private String message;
    private T data;

    private static final Integer SUCCESS_CODE = AsproEnums.SystemCode.SYSTEM_SUCCESS.getCode();
    private static final Integer ERROR_CODE = AsproEnums.SystemCode.SYSTEM_ERROR.getCode();

    private static final String SUCCESS_MSG = AsproEnums.SystemCode.SYSTEM_SUCCESS.getMessage();
    private static final String ERROR_MSG = AsproEnums.SystemCode.SYSTEM_ERROR.getMessage();

    public static final AsproResponse<Void> SUCCESS = AsproResponse.<Void>builder().code(SUCCESS_CODE).message(SUCCESS_MSG).build();

    public static <T> AsproResponse<T> success(T data) {
        return AsproResponse.<T>builder()
                .code(SUCCESS_CODE).message(SUCCESS_MSG).data(data)
                .build();
    }

    public static <T> AsproResponse<T> result(AsproEnums.SystemCode systemCode) {
        return AsproResponse.<T>builder()
                .code(systemCode.getCode()).message(systemCode.getMessage())
                .build();
    }
}
