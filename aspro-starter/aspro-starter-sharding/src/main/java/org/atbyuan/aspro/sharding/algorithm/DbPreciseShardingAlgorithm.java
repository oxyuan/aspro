package org.atbyuan.aspro.sharding.algorithm;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author atbyuan
 * @since 2022/9/27 15:47
 */

@Slf4j
public class DbPreciseShardingAlgorithm extends AbstractPreciseShardingAlgorithm<Integer> {

    // 主库别名
    private static final String DBM = "ds1";

    /**
     * 精准分库策略
     *
     * @param dbNames              所有的数据源
     * @param preciseShardingValue SQL执行时传入的分片值
     * @return 目标数据源
     */
    @Override
    public String doSharding(Collection<String> dbNames, PreciseShardingValue<Integer> preciseShardingValue) {
        init();
        //真实节点
        dbNames.forEach(a -> log.info("actual node db: [{}]", a));

        log.info("logic table name: [{}], route column: [{}]", preciseShardingValue.getLogicTableName(), preciseShardingValue.getColumnName());

        // 若走主库，直接返回主库
        if (dbNames.size() == 1) {
            if (DBM.equals(dbNames.iterator().next())) {
                return DBM;
            }
        }

        //精确分片
        log.info("column name: [{}]", preciseShardingValue.getValue());

        for (String availableTargetName : dbNames) {
            Integer value = preciseShardingValue.getValue();
            if (("ds" + value % (shardingSliceProperties.getDatabase() + 1)).equals(availableTargetName)) {
                log.info("actual ds: [{}]", availableTargetName);
                return availableTargetName;
            }
        }
        return null;
    }

}