package org.atbyuan.aspro.govern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2022/4/25 09:25
 */
@Slf4j
@SpringBootApplication
public class GovernAsproApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernAsproApplication.class, args);
        log.info("=====>>> govern-aspro start success <<<=====");
    }

}
