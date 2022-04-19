package org.atbyuan.aspro.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author atbyuan
 * @since 2022/4/18 13:19
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AsproGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsproGatewayApplication.class, args);
        log.info("=====>>> aspro-gateway start success <<<=====");
    }

}
