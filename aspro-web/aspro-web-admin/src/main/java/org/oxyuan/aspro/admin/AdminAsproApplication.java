package org.oxyuan.aspro.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author oxyuan
 * @since 2022/4/18 13:24
 */
@Slf4j
@SpringBootApplication
public class AdminAsproApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminAsproApplication.class, args);
        log.info("=====>>> admin-aspro start success <<<=====");
    }

}
