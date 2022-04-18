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
        OTHER(0, "其他"),
        VIDEO(1, "视频"),
        VOICE(2, "音频"),
        FILE(3, "文件"),
        PAGE(4, "网页"),
        IMAGE(5, "图片"),
        ;

        private final int code;
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
