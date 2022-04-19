package org.atbyuan.aspro.api.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author atbyuan
 * @since 2022/4/19 20:32
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AsproApiController {

    @GetMapping("/random")
    public ApiResponse<Integer> random() {
        return ApiResponse.success(RandomUtil.randomInt());
    }

}
