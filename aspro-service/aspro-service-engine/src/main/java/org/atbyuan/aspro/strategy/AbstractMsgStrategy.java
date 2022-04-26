package org.atbyuan.aspro.strategy;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.atbyuan.aspro.common.enums.MsgEnums;
import org.atbyuan.aspro.common.pojo.entity.MsgConfig;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;
import org.atbyuan.aspro.common.tls.ContextHolder;
import org.atbyuan.aspro.db.repository.MsgConfigRepository;
import org.atbyuan.aspro.db.repository.MsgRecordRepository;
import org.atbyuan.aspro.factory.MsgStrategyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author atbyuan
 * @since 2022/4/24 21:06
 */
@Slf4j
@Component
public abstract class AbstractMsgStrategy implements MsgStrategy {

    @Resource
    private MsgConfigRepository msgConfigRepository;
    @Resource
    private MsgRecordRepository msgRecordRepository;

    @PostConstruct
    public void init() {
        log.info("init msg strategy. {}", this.getClass().getSimpleName());
        MsgStrategyFactory.addStrategy(this);
    }

    /**
     * 消息处理流程 [postProcessBeforeHandle -> doHandle -> postProcessAfterHandle]
     *
     * @param msgList: 待处理消息列表
     */
    @Override
    public void execute(List<MsgRecord> msgList) {
        if (CollUtil.isEmpty(msgList)) {
            log.info("msgList is empty");
            return;
        }
        // 前置处理
        postProcessBeforeHandle(msgList);

        // 消息处理
        long startTime = System.currentTimeMillis();

        Map<Integer, List<MsgRecord>> configMsgMap = msgList.stream().collect(Collectors.groupingBy(MsgRecord::getConfigId));

        Map<Integer, String> configMap = ContextHolder.Config.get();

        configMsgMap.forEach((configId, msgRecordList) -> {
            // 循环处理消息
            for (MsgStrategy strategy : MsgStrategyFactory.getStrategies(configMap.get(configId))) {
                try {

                    strategy.doHandle(msgRecordList);

                } catch (Exception e) {
                    log.error("an exception occurred while processing the message. {}", ExceptionUtils.getMessage(e));
                }
            }
        });

        log.info("msg doHandle: [" + msgList.size() + "]. cost: [" + (System.currentTimeMillis() - startTime) + "ms] ");

        // 后置处理
        postProcessAfterHandle(msgList);
    }

    /**
     * 具体的消息工具逻辑处理，统一调用
     *
     * @param msgList   消息列表
     * @param predicate 处理逻辑
     */
    protected void doHandle(List<MsgRecord> msgList, Predicate<List<String>> predicate) {
        String strategyClassName = this.getClass().getSimpleName();
        log.info("{} doHandle. msgList: {}", strategyClassName, msgList);
        for (MsgRecord msgRecord : msgList) {
            String content = msgRecord.getContent();
            if (StringUtils.isBlank(content)) {
                continue;
            }
            List<String> stringList = convert(content);

            boolean status = predicate.test(stringList);

            if (!status) {
                log.error("{} doHandle failed. msgId: {}", strategyClassName, msgRecord.getId());
                // TODO.
                continue;
            }

            log.info("{} doHandle. stringList: {}", strategyClassName, stringList);
        }
    }

    /**
     * 消息统一前置处理
     *
     * @param msgList 消息列表
     */
    protected void postProcessBeforeHandle(List<MsgRecord> msgList) {
        // 1. 消息入库
        Lists.partition(msgList, 50).forEach(msgRecordList -> msgRecordRepository.saveBatch(msgList));
        // 2. 获取消息的配置信息
        List<Integer> configIdList = msgList.stream().map(MsgRecord::getConfigId).distinct().collect(Collectors.toList());
        if (CollUtil.isEmpty(configIdList)) {
            return;
        }
        List<MsgConfig> configList = msgConfigRepository.selectBatchIds(configIdList);
        if (CollUtil.isEmpty(configList)) {
            return;
        }
        Map<Integer, String> configContentMap = configList.stream().collect(Collectors.toMap(MsgConfig::getId, MsgConfig::getContent));
        ContextHolder.Config.init(configContentMap);
    }

    /**
     * 消息统一后置处理
     *
     * @param msgList 消息列表
     */
    protected void postProcessAfterHandle(List<MsgRecord> msgList) {

        ContextHolder.Config.clear();
    }

    /**
     * 根据消息类型获取消息体
     *
     * @param content 消息内容
     * @return 消息体
     */
    private List<String> convert(String content) {
        List<String> rs = Lists.newArrayList();
        MsgEnums.Category category = getType();
        Optional.of(content)
                .map(JSON::parseObject)
                .map(jo -> jo.getJSONArray(category.getFiled()))
                .ifPresent(ja -> {
                    for (Object o : ja) {
                        rs.add(o.toString());
                    }
                });
        return rs;
    }
}
