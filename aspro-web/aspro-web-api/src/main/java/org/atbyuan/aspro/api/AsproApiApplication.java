package org.atbyuan.aspro.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2022/4/18 13:24
 */
@Slf4j
@SpringBootApplication
public class AsproApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsproApiApplication.class, args);
        log.info("=====>>> aspro-api start success <<<=====");
    }

}
