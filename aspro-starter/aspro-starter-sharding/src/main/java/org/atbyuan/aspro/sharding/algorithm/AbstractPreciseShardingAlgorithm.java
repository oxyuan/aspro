package org.atbyuan.aspro.sharding.algorithm;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.atbyuan.aspro.sharding.properties.ShardingSliceProperties;

import java.util.Collection;
import java.util.Properties;

/**
 * @author atbyuan
 * @since 2022/9/29 13:22
 */
@Slf4j
public abstract class AbstractPreciseShardingAlgorithm<T extends Comparable<?>> implements StandardShardingAlgorithm<T> {

    protected static volatile ShardingSliceProperties shardingSliceProperties;

    public void init() {
        if (shardingSliceProperties == null) {
            synchronized (AbstractPreciseShardingAlgorithm.class) {
                if (shardingSliceProperties == null) {
                    shardingSliceProperties = SpringUtil.getBean(ShardingSliceProperties.class);
                }
            }
        }
    }

    /**
     * 范围分表策略
     *
     * @param names              所有的数据源
     * @param rangeShardingValue SQL执行时传入的分片值
     * @return 目标数据源
     */
    @Override
    public Collection<String> doSharding(Collection<String> names, RangeShardingValue<T> rangeShardingValue) {
        return null;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {

    }

}
