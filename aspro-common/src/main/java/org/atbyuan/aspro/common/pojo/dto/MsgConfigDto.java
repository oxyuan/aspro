package org.atbyuan.aspro.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author atbyuan
 * @since 2022/4/25 18:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgConfigDto {

    private Integer code;
    private Boolean enabled;

}
