package org.atbyuan.aspro.sharding.utools;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.atbyuan.aspro.sharding.mapper.SchemaMapper;
import org.atbyuan.aspro.sharding.properties.ShardingSliceProperties;
import org.atbyuan.aspro.sharding.tls.ContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.atbyuan.aspro.sharding.constant.ShardingConst.DEF_LOGIN_DATABASE;

/**
 * @author atbyuan
 * @since 2022-10-03 21:38
 */
@Component
public class ShardingAlgorithmTool {

    // <tableName, {database_source}>
    private static final Map<String, HashSet<String>> TABLE_NAME_CACHE = Maps.newHashMap();

    @Resource
    private ShardingSliceProperties shardingSliceProperties;
    @Resource
    private SchemaMapper schemaMapper;

    /**
     * 判断 分表获取的表名是否存在 不存在则自动建表
     *
     * @param logicTableName  逻辑表名(表头)
     * @param resultTableName 真实表名
     * @return 确认存在于数据库中的真实表名
     */
    public String shardingTablesCheckAndCreatAndReturn(String logicTableName, String resultTableName) {
        synchronized (logicTableName.intern()) {
            HashSet<String> dbSource = TABLE_NAME_CACHE.getOrDefault(resultTableName, Sets.newHashSet());
            // 缓存中有此表 返回
            if (dbSource.contains(ContextHolder.dbs())) {
                return resultTableName;
            }
            // 缓存中无此表 建表 并添加缓存
            // 调用mapper 创建表
            schemaMapper.createTable(resultTableName);
            dbSource.add(ContextHolder.dbs());
            TABLE_NAME_CACHE.put(resultTableName, dbSource);
        }
        return resultTableName;
    }

    /**
     * 缓存重载方法
     */
    public void tableNameCacheReload() {
        // 读取数据库中所有表名
        List<String> tableNameList = getAllTableNameBySchema();
        // 删除旧的缓存(如果存在)
        ShardingAlgorithmTool.TABLE_NAME_CACHE.clear();
        // 写入新的缓存
        Integer database = shardingSliceProperties.getDatabase();
        for (String tableName : tableNameList) {
            HashSet<String> dbs = Sets.newHashSet();
            for (int i = 1; i <= database; i++) {
                dbs.add(DEF_LOGIN_DATABASE + i);
            }
            ShardingAlgorithmTool.TABLE_NAME_CACHE.put(tableName, dbs);
        }
    }

    /**
     * 获取数据库中的表名
     */
    public List<String> getAllTableNameBySchema() {
        List<String> res = Lists.newArrayList();
        // 获取数据中的表名，需要自己构建数据源 SHOW TABLES like 'msg_%'
        try {
            res = schemaMapper.showTableInSchema("msg_");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean contains(String tableName) {
        return TABLE_NAME_CACHE.getOrDefault(tableName, new HashSet<>()).contains(ContextHolder.dbs());
    }

}
