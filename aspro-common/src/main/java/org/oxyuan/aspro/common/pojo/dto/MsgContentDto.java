package org.oxyuan.aspro.common.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author oxyuan
 * @since 2022/4/20 08:53
 */
@NoArgsConstructor
@Data
public class MsgContentDto {

    private List<String> video;

    private List<String> audio;

    private List<String> file;

    private List<String> page;

    private ExtDTO ext;

    @NoArgsConstructor
    @Data
    public static class ExtDTO {
        private String headImg;
        private String nickName;
    }
}
