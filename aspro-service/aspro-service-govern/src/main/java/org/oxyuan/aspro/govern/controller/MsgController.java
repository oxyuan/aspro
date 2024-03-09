package org.oxyuan.aspro.govern.controller;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.oxyuan.aspro.common.pojo.entity.Msg;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.oxyuan.aspro.govern.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author oxyuan
 * @since 2022/9/28 10:30
 */
@Slf4j
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @PostMapping("/save")
    public AsproResponse<Msg> save() {
        return AsproResponse.success(msgService.save(msg()));
    }

    @DeleteMapping("/delete")
    public AsproResponse<Void> delete(@RequestParam(value = "id") Long id) {
        msgService.delete(id);
        return AsproResponse.SUCCESS;
    }

    @PutMapping("/update")
    public int update(@RequestBody Msg msg) {
        return msgService.update(msg);
    }

    @GetMapping("/list")
    public AsproResponse<PageInfo<Msg>> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                             @RequestParam(value = "configId", required = false) Integer configId) {
        return AsproResponse.success(msgService.getList(pageNum, pageSize, configId));
    }

    @PostMapping("/batch")
    public AsproResponse<Integer> batch(@RequestParam(value = "num", required = false, defaultValue = "1") Integer num) {
        List<Msg> list = Lists.newArrayList();
        while (num-- > 0) {
            list.add(msg());
        }
        return AsproResponse.success(msgService.insertBatch(list));
    }

    @PostMapping("/upsert")
    public AsproResponse<Integer> upsert(@RequestParam(value = "num", required = false, defaultValue = "1") Integer num) {
        List<Msg> list = Lists.newArrayList();
        while (num-- > 0) {
            list.add(msg());
        }
        return AsproResponse.success(msgService.upsertBatch(list));
    }

    private Msg msg() {
        int randomInt = RandomUtil.randomInt(1, 11);
        return Msg.builder()
                .configId(randomInt)
                .title("title-" + RandomUtil.randomString(3))
                .content("this is a random str: " + RandomUtil.randomString(randomInt))
                .releaseTime(new Date())
                .uid(RandomUtil.randomInt(100, 1000000))
                .nickname("nickname-" + RandomUtil.randomString(5).toLowerCase())
                .msgStr(RandomUtil.randomString(randomInt))
                .build();
    }
}
