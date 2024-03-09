package org.oxyuan.aspro.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author oxyuan
 * @since 2022/4/18 13:24
 */
@Slf4j
@SpringBootApplication
public class ApiAsproApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAsproApplication.class, args);
        log.info("=====>>> api-aspro start success <<<=====");
    }

}
