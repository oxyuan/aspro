package org.atbyuan.aspro.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2022/4/18 13:24
 */
@Slf4j
@SpringBootApplication
public class AsproAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsproAdminApplication.class, args);
        log.info("=====>>> aspro-admin start success <<<=====");
    }

}
