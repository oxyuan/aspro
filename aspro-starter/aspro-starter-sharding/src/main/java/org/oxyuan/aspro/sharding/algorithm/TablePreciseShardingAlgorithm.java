package org.oxyuan.aspro.sharding.algorithm;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.oxyuan.aspro.sharding.utools.ShardingAlgorithmTool;

import java.util.Collection;
import java.util.Date;

import static org.oxyuan.aspro.sharding.constant.ShardingConst.DEF_LOGIN_TABLE;

/**
 * @author oxyuan
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

        String resultTableName = DEF_LOGIN_TABLE + "_" + suffix;

        log.info("actual table: [[{}]]", resultTableName);
        for (String availableTargetName : tableNames) {
            if ((resultTableName).equals(availableTargetName) && ShardingAlgorithmTool.contains(resultTableName)) {
                return availableTargetName;
            }
        }
        if (!tableNames.contains(resultTableName)) {
            tableNames.add(resultTableName);
        }
        return shardingAlgorithmTool.shardingTablesCheckAndCreatAndReturn(DEF_LOGIN_TABLE, resultTableName);
    }

}