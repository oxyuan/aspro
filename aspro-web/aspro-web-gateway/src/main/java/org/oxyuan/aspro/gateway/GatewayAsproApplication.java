package org.oxyuan.aspro.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author oxyuan
 * @since 2022/4/18 13:19
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GatewayAsproApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAsproApplication.class, args);
        log.info("=====>>> gateway-aspro start success <<<=====");
    }

}
