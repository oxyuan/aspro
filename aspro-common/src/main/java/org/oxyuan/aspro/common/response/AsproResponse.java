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

    public static <T> AsproResponse<T> success(T data) {
        AsproEnums.SystemCode success = AsproEnums.SystemCode.SYSTEM_SUCCESS;
        return AsproResponse.<T>builder()
                .code(success.getCode()).message(success.getMessage()).data(data)
                .build();
    }

    public static AsproResponse<Void> success() {
        return result(AsproEnums.SystemCode.SYSTEM_SUCCESS);
    }

    public static <T> AsproResponse<T> result(AsproEnums.SystemCode systemCode) {
        return AsproResponse.<T>builder()
                .code(systemCode.getCode()).message(systemCode.getMessage())
                .build();
    }
}
