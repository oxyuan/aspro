package org.oxyuan.aspro.admin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.oxyuan.aspro.common.exception.BusinessException;
import org.oxyuan.aspro.common.pojo.entity.MsgConfig;
import org.oxyuan.aspro.common.pojo.entity.MsgRecord;
import org.oxyuan.aspro.common.response.AsproResponse;
import org.oxyuan.aspro.db.mapper.MsgConfigMapper;
import org.oxyuan.aspro.db.mapper.MsgRecordMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author oxyuan
 * @since 2022/4/18 17:59
 */
@RestController
@RequestMapping("/msg")
public class MsgAdminController {

    @Resource
    private MsgRecordMapper msgRecordMapper;
    @Resource
    private MsgConfigMapper msgConfigMapper;

    @PostMapping("/add")
    public AsproResponse<Void> add(MsgRecord msgRecord) {
        Integer configId = msgRecord.getConfigId();
        if (configId == null) {
            throw new BusinessException("configId不能为空");
        }
        MsgConfig msgConfig = msgConfigMapper.selectById(configId);
        if (msgConfig == null) {
            throw new BusinessException("configId不存在");
        }

        msgRecordMapper.insert(msgRecord);
        return AsproResponse.SUCCESS;
    }

    @GetMapping("/config/query")
    public AsproResponse<MsgConfig> configQuery(@RequestParam("configId") Integer configId) {
        return AsproResponse.success(msgConfigMapper.selectById(configId));
    }

    @GetMapping("/dateFormat")
    public AsproResponse<String> dateFormat(@RequestParam("date") Date date) {
        DateTime dateTime = DateUtil.parse(date.toString(), "yyyy-MM-dd HH:mm:ss");

        return AsproResponse.success(dateTime.toString());
    }

    /**
     * {"type": [1, 2, 3, 4, 5],"content": {"video": ["1.mp4", "2.mp4"],"audio": ["1.mp3", "2.mp3"],"file": ["1.pdf", "2.pdf"],"page": ["www.baidu.com", "www.taobao.com"],"ext": {"headImg": "1.jpg","nickName": "昵称"}},"msgStr": ""}
     *
     * @param msg 消息内容
     * @return 处理结果
     */
    @RequestMapping("/test")
    public AsproResponse<Void> push(@RequestParam("msg") String msg) {

        return AsproResponse.SUCCESS;
    }


}
