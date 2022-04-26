package org.atbyuan.aspro.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author atbyuan
 * @since 2022/4/18 13:38
 */
public interface MsgEnums {

    @Getter
    @AllArgsConstructor
    enum Category {
        DEFAULT(0, "", "初始配置"),
        TEXT(1, "text", "文本"),
        VIDEO(2, "video", "视频"),
        AUDIO(3, "audio", "音频"),
        FILE(4, "file", "文件"),
        PAGE(5, "page", "网页"),
        IMAGE(6, "image", "图片"),
        OTHER(7, "ext", "其他"),
        ;

        private final int code;
        private final String filed;
        private final String desc;

        public static Category of(int code) {
            for (Category codeEnum : values()) {
                if (codeEnum.getCode() == code) {
                    return codeEnum;
                }
            }
            return OTHER;
        }
    }

}
