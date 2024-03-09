package org.oxyuan.aspro.sharding.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.sharding.utools.ShardingAlgorithmTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author oxyuan
 * @since 2022-10-03 21:18
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AutoConfigDateNodes implements CommandLineRunner {

    @Autowired
    private ShardingAlgorithmTool shardingAlgorithmTool;


    @Override
    public void run(String... args) {
        shardingAlgorithmTool.tableNameCacheReload();
    }

}
