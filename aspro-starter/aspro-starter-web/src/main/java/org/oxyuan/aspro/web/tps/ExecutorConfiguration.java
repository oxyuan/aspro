package org.oxyuan.aspro.web.tps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author oxyuan
 * @since 2022/4/27 23:39
 */
@Slf4j
@Configuration
public class ExecutorConfiguration {

    private static final Integer CORES = Runtime.getRuntime().availableProcessors();

    @Bean("corePriorThreadPool")
    public ExecutorService newCorePriorThreadPool() {
        return new ThreadPoolExecutor(CORES, CORES * 2, 1L, TimeUnit.MINUTES, new PriorityBlockingQueue<>());
    }

}
