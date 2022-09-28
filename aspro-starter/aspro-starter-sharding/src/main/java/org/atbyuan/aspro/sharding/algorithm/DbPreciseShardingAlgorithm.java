package org.atbyuan.aspro.sharding.algorithm;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author atbyuan
 * @since 2022/9/27 15:47
 */

@Slf4j
public class DbPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    /**
     * 分片策略
     *
     * @param availableTargetNames 所有的数据源
     * @param preciseShardingValue SQL执行时传入的分片值
     * @return 目标数据源
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> preciseShardingValue) {
        //真实节点
        availableTargetNames.forEach(a -> log.info("actual node db: [{}]", a));

        log.info("logic table name: [{}], route column: [{}]", preciseShardingValue.getLogicTableName(), preciseShardingValue.getColumnName());

        //精确分片
        log.info("column name: [{}]", preciseShardingValue.getValue());

        for (String availableTargetName : availableTargetNames) {
            Integer value = preciseShardingValue.getValue();
            if (("ds" + value % 2).equals(availableTargetName)) {
                log.info("actual ds: [{}]", availableTargetName);
                return availableTargetName;
            }
        }
        return null;
    }

}