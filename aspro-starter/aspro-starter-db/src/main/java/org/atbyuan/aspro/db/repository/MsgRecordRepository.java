package org.atbyuan.aspro.db.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.aspro.common.pojo.entity.MsgRecord;
import org.atbyuan.aspro.db.mapper.MsgRecordMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author atbyuan
 * @since 2022/4/19 18:25
 */
@Slf4j
@Repository
public class MsgRecordRepository extends ServiceImpl<MsgRecordMapper, MsgRecord> {

    @Resource
    private MsgRecordMapper msgRecordMapper;

    @Override
    public MsgRecordMapper getBaseMapper() {
        return this.msgRecordMapper;
    }

}
