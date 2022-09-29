package org.atbyuan.aspro.sharding.algorithm;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @author atbyuan
 * @since 2022/9/27 15:48
 */
@Slf4j
public class TablePreciseShardingAlgorithm extends AbstractPreciseShardingAlgorithm<Date> {

    /**
     * 精准分表规则
     *
     * @param tableNames           所有的分片表
     * @param preciseShardingValue SQL执行时传入的分片值
     * @return 目标表
     */
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Date> preciseShardingValue) {
        init();
        //真实节点
        tableNames.forEach(a -> log.info("actual node table: [{}]", a));
        log.info("logic table name: [{}], route column: [{}]", preciseShardingValue.getLogicTableName(), preciseShardingValue.getColumnName());

        // 精确分片
        Date value = preciseShardingValue.getValue();
        log.info("column value: [{}]", value);

        int second = DateUtil.millisecond(value);
        int suffix = second == 0 ? 1 : (second % shardingSliceProperties.getTable()) + 1;

        for (String availableTargetName : tableNames) {
            if (("msg_" + suffix).equals(availableTargetName)) {
                log.info("actual table: [[{}]]", availableTargetName);
                return availableTargetName;
            }
        }
        return null;
    }

}