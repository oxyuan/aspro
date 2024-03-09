package org.oxyuan.aspro.factory;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.oxyuan.aspro.common.enums.MsgEnums.Category;
import org.oxyuan.aspro.common.utils.MsgConfigUtil;
import org.oxyuan.aspro.strategy.MsgStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author oxyuan
 * @since 2022/4/24 20:55
 */
public class MsgStrategyFactory {

    private static MsgStrategyFactory instance;

    private final Map<Category, MsgStrategy> strategyMap = new ConcurrentHashMap<>();

    public static void addStrategy(MsgStrategy strategy) {
        MsgStrategyFactory fact = MsgStrategyFactory.getInstance();
        fact.strategyMap.put(strategy.getType(), strategy);
    }

    /**
     * 根据消息类型获取对应的策略
     *
     * @param type 消息类型
     * @return 单个消息处理策略
     */
    public static Optional<MsgStrategy> getStrategy(Category type) {
        return Optional.ofNullable(MsgStrategyFactory.getInstance().strategyMap.get(type));
    }

    /**
     * 根据配置的消息类型批量获取队对应的策略
     *
     * @param configs 消息类型配置
     * @return 多个消息处理策略
     */
    public static List<MsgStrategy> getStrategies(String configs) {
        List<MsgStrategy> strategies = Lists.newArrayList();
        if (StringUtils.isBlank(configs)) {
            return strategies;
        }
        List<Category> types = MsgConfigUtil.convert(configs);
        if (CollUtil.isEmpty(types)) {
            return strategies;
        }
        types.forEach(type -> strategies.add(MsgStrategyFactory.getInstance().strategyMap.get(type)));
        return strategies;
    }

    private static synchronized MsgStrategyFactory getInstance() {
        if (instance == null) {
            instance = new MsgStrategyFactory();
        }
        return instance;
    }

}
