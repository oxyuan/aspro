package org.atbyuan.aspro.strategy;

import org.atbyuan.aspro.common.enums.MsgEnums.Category;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022/4/24 20:52
 */
public interface MsgStrategy {

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    Category getType();

    /**
     * 消息全流程
     *
     * @param msgList: 待处理消息列表
     */
    void execute(List<MsgRecord> msgList);

    /**
     * 消息处理
     *
     * @param msgList: 待处理消息列表
     */
    void doHandle(List<MsgRecord> msgList);

}
