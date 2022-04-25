package org.atbyuan.aspro.strategy;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.enums.MsgEnums;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022/4/25 09:07
 */
@Slf4j
@Component
public class ImageMsgStrategy extends AbstractMsgStrategy{

    @Override
    public MsgEnums.Category getType() {
        return MsgEnums.Category.VIDEO;
    }

    @Override
    public void doHandle(List<MsgRecord> msgList) {
        doHandle(msgList, (msg) -> {
            // 「图片」消息的处理逻辑


            return false;
        });
    }
}

