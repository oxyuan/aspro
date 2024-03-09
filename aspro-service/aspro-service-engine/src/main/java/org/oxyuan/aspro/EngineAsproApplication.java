package org.oxyuan.aspro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author oxyuan
 * @since 2022/4/25 09:25
 */
@Slf4j
@SpringBootApplication
public class EngineAsproApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineAsproApplication.class, args);
        log.info("=====>>> engine-aspro start success <<<=====");
    }

}
