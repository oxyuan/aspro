package org.atbyuan.aspro.govern.controller;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.response.AsproResponse;
import org.atbyuan.aspro.govern.service.SchemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author atbyuan
 * @since 2022-10-04 00:25
 */
@Slf4j
@RestController
@RequestMapping("schema")
public class SchemaController {

    @Resource
    private SchemaService schemaService;

    @GetMapping("/show")
    public AsproResponse<List<String>> showTable(@RequestParam("tableName") String tableName) {
        return AsproResponse.success(schemaService.showTable(tableName));
    }

    @PostMapping("/create")
    public AsproResponse<Void> createTable(@RequestParam("tableName") String tableName) {
        schemaService.createTable(tableName);
        return AsproResponse.SUCCESS;
    }

}
