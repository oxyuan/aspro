package org.atbyuan.aspro.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atbyuan.aspro.common.enums.AsproEnums;

/**
 * @author atbyuan
 * @since 2022/4/18 18:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T data;

    private static final Integer SUCCESS_CODE = AsproEnums.SystemCode.SYSTEM_SUCCESS.getCode();
    private static final Integer ERROR_CODE = AsproEnums.SystemCode.SYSTEM_ERROR.getCode();

    private static final String SUCCESS_MSG = AsproEnums.SystemCode.SYSTEM_SUCCESS.getMessage();
    private static final String ERROR_MSG = AsproEnums.SystemCode.SYSTEM_ERROR.getMessage();

    public static final ApiResponse<Void> SUCCESS = ApiResponse.<Void>builder().code(SUCCESS_CODE).message(SUCCESS_MSG).build();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(SUCCESS_CODE).message(SUCCESS_MSG).data(data)
                .build();
    }

    public static <T> ApiResponse<T> result(AsproEnums.SystemCode systemCode) {
        return ApiResponse.<T>builder()
                .code(systemCode.getCode()).message(systemCode.getMessage())
                .build();
    }
}
