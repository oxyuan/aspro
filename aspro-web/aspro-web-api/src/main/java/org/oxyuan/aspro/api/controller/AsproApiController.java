package org.oxyuan.aspro.api.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oxyuan
 * @since 2022/4/19 20:32
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AsproApiController {

    @GetMapping("/random")
    public AsproResponse<Integer> random() {
        return AsproResponse.success(RandomUtil.randomInt(10, 100));
    }

}
