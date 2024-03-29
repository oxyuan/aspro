package org.oxyuan.aspro.strategy;

import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.enums.MsgEnums;
import org.oxyuan.aspro.common.pojo.entity.MsgRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author oxyuan
 * @since 2022/4/25 09:02
 */
@Slf4j
@Component
public class VideoMsgStrategy extends AbstractMsgStrategy{

    @Override
    public MsgEnums.Category getType() {
        return MsgEnums.Category.VIDEO;
    }

    @Override
    public void doHandle(List<MsgRecord> msgList) {
        doHandle(msgList, (msg) -> {
            // 「视频」消息的处理逻辑


            return false;
        });
    }
}
