package org.atbyuan.aspro.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.atbyuan.aspro.common.pojo.entity.Msg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022/9/28 10:41
 */
@Repository
public interface MsgMapper extends BaseMapper<Msg> {

    Integer insertBatch(List<Msg> list);

    Integer upsertBatch(List<Msg> list);
}
