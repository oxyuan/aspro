package org.oxyuan.aspro.sharding.algorithm;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.oxyuan.aspro.sharding.tls.ContextHolder;

import java.util.Collection;

import static org.oxyuan.aspro.sharding.constant.ShardingConst.DBM;

/**
 * @author oxyuan
 * @since 2022/9/27 15:47
 */

@Slf4j
public class DbPreciseShardingAlgorithm extends AbstractPreciseShardingAlgorithm<Integer> {

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

        int shardingPartition = preciseShardingValue.getValue() % (shardingSliceProperties.getDatabase());
        String dbShardingName = "ds" + (shardingPartition + 1);

        ContextHolder.init(dbShardingName);

        for (String availableTargetName : dbNames) {
            if ((dbShardingName).equals(availableTargetName)) {
                log.info("actual ds: [[{}]]", availableTargetName);
                return availableTargetName;
            }
        }
        return null;
    }

}