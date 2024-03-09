package org.oxyuan.aspro.govern.service;

import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.sharding.mapper.SchemaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxyuan
 * @since 2022-10-04 00:23
 */
@Slf4j
@Service
public class SchemaService {

    @Resource
    private SchemaMapper schemaMapper;

    public List<String> showTable(String tableName) {
        return schemaMapper.showTableInSchema(tableName);
    }

    public void createTable(String tableName) {
        schemaMapper.createTable(tableName);
    }

}
