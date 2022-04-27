package org.atbyuan.aspro.strategy;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.enums.MsgEnums;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022/4/25 15:46
 */
@Slf4j
@Component
public class EmptyMsgStrategy extends AbstractMsgStrategy {

    @Override
    public MsgEnums.Category getType() {
        return MsgEnums.Category.EMPTY;
    }

    @Override
    public void doHandle(List<MsgRecord> msgList) {}
}
