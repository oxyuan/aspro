package org.oxyuan.aspro.sharding;

import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.sharding.config.MybatisConfiguration;
import org.oxyuan.aspro.sharding.config.ShardingConfiguration;
import org.oxyuan.aspro.sharding.init.AutoConfigDateNodes;
import org.oxyuan.aspro.sharding.properties.ShardingSliceProperties;
import org.oxyuan.aspro.sharding.utools.ShardingAlgorithmTool;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author oxyuan
 * @since 2022/9/28 9:41
 */
@Slf4j
@Configuration
@Import({
        ShardingConfiguration.class,
        MybatisConfiguration.class,
        ShardingSliceProperties.class,
        ShardingAlgorithmTool.class,
        AutoConfigDateNodes.class
})
public class ShardingAutoConfiguration {

}