package org.oxyuan.aspro.govern.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.pojo.entity.Msg;
import org.oxyuan.aspro.sharding.mapper.MsgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxyuan
 * @since 2022/9/28 10:33
 */
@Slf4j
@Service
public class MsgService {

    @Resource
    private MsgMapper msgMapper;

    public void delete(Long id) {
        msgMapper.deleteById(id);
    }

    public Msg save(Msg msg) {
        msgMapper.insert(msg);
        return msgMapper.selectById(msg.getId());
    }

    public Integer update(Msg msg) {
        return msgMapper.updateById(msg);
    }

    public Integer insertBatch(List<Msg> msgList) {
        return msgMapper.insertBatch(msgList);
    }


    public Integer upsertBatch(List<Msg> msgList) {
        return msgMapper.upsertBatch(msgList);
    }

    public PageInfo<Msg> getList(Integer pageNum, Integer pageSize, Integer configId) {
        // 条件参数封装
        Msg msg = Msg.builder().configId(configId).build();
        Wrapper<Msg> queryWrapper = new LambdaQueryWrapper<>(msg);
        // 分页
        PageMethod.startPage(pageNum, pageSize);
        List<Msg> msgList = msgMapper.selectList(queryWrapper);
        return PageInfo.of(msgList);
    }

}
