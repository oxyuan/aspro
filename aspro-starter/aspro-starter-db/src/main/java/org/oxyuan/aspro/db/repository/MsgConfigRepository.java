package org.oxyuan.aspro.db.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.pojo.entity.MsgConfig;
import org.oxyuan.aspro.db.mapper.MsgConfigMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxyuan
 * @since 2022/4/19 18:23
 */
@Slf4j
@Repository
public class MsgConfigRepository extends ServiceImpl<MsgConfigMapper, MsgConfig> {

    @Resource
    private MsgConfigMapper msgConfigMapper;

    public List<MsgConfig> selectBatchIds(List<Integer> configIdList) {
        return msgConfigMapper.selectBatchIds(configIdList);
    }

}
