package org.atbyuan.aspro.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.atbyuan.aspro.common.pojo.entity.Msg;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022-10-03 23:09
 */
@Mapper
public interface SchemaMapper extends BaseMapper<Msg> {

    @Select("SHOW TABLES like '${tableName}%'")
    List<String> showTableInSchema(String tableName);

    @Update("CREATE TABLE IF NOT EXISTS ${tableName}\n" +
            "(\n" +
            "    id           bigint                                not null\n" +
            "        primary key,\n" +
            "    config_id    int                                   not null comment '配置ID',\n" +
            "    title        varchar(128)                          not null comment '消息标题',\n" +
            "    content      varchar(512)                          not null comment '消息内容',\n" +
            "    release_time datetime    default CURRENT_TIMESTAMP not null comment '发布时间',\n" +
            "    uid          int                                   not null comment '用户ID',\n" +
            "    nickname     varchar(128)                          null comment '用户昵称',\n" +
            "    msg_str      varchar(128)                          null comment '透传字段',\n" +
            "    revision     int         default 0                 not null comment '版本号',\n" +
            "    create_time  datetime    default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
            "    creator      varchar(16) default 'admin'           not null comment '创建者',\n" +
            "    update_time  datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
            "    updater      varchar(16) default 'admin'           not null comment '更新者',\n" +
            "    index idx_conf (config_id),\n" +
            "    index idx_nickname (nickname)\n" +
            ")\n" +
            "    comment '消息数据'")
    void createTable(String tableName);

}
