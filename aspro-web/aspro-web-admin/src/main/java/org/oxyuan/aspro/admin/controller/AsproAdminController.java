package org.oxyuan.aspro.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author oxyuan
 * @since 2022/4/18 13:33
 */
@RestController
@RequestMapping("/admin")
public class AsproAdminController {

    @PostMapping("/valid")
    public AsproResponse<Void> valid(@RequestBody Request request) {
        return AsproResponse.success();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private String name;
        private Integer age;
        private String address;
    }

}
