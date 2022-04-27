package org.atbyuan.aspro.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;
import org.atbyuan.aspro.common.response.AsproResponse;
import org.atbyuan.aspro.strategy.EmptyMsgStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author atbyuan
 * @since 2022/4/25 09:57
 */
@Slf4j
@RestController
@RequestMapping("/engine")
public class EngineController {

    @Resource
    private EmptyMsgStrategy emptyMsgStrategy;

    @PostMapping("/send")
    public AsproResponse<Void> send(MsgRecord msgRecord) {
        log.info("send msg: {}", msgRecord);

        emptyMsgStrategy.execute(Lists.newArrayList(msgRecord));

        return AsproResponse.SUCCESS;
    }

}
