package org.oxyuan.aspro.sharding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.oxyuan.aspro.sharding.properties.ShardingSliceProperties.SHARDING_SLICE;

/**
 * @author oxyuan
 * @since 2022/9/29 13:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = SHARDING_SLICE)
public class ShardingSliceProperties {
    public static final String SHARDING_SLICE = "sharding.slice";

    private Integer database = 2;
    private Integer table = 3;

}
