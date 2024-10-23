package org.oxyuan.aspro.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.pojo.entity.MsgRecord;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.oxyuan.aspro.strategy.EmptyMsgStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author oxyuan
 * @since 2022/4/25 09:57
 */
@Slf4j
@RestController
@RequestMapping("/engine")
public class EngineController {

    @Resource
    private EmptyMsgStrategy emptyMsgStrategy;

    @PostMapping("/send")
    public AsproResponse<Void> send(@RequestBody MsgRecord msgRecord) {
        log.info("send msg: {}", msgRecord);

        emptyMsgStrategy.execute(Lists.newArrayList(msgRecord));

        return AsproResponse.success();
    }

}
