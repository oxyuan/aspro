package org.atbyuan.aspro.admin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.atbyuan.aspro.common.entity.MsgConfig;
import org.atbyuan.aspro.common.entity.MsgRecord;
import org.atbyuan.aspro.common.exception.BusinessException;
import org.atbyuan.aspro.common.response.ApiResponse;
import org.atbyuan.aspro.db.mapper.MsgConfigMapper;
import org.atbyuan.aspro.db.mapper.MsgRecordMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author atbyuan
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
    public ApiResponse<Void> add(MsgRecord msgRecord) {
        Integer configId = msgRecord.getConfigId();
        if (configId == null) {
            throw new BusinessException("configId不能为空");
        }
        MsgConfig msgConfig = msgConfigMapper.selectById(configId);
        if (msgConfig == null) {
            throw new BusinessException("configId不存在");
        }

        msgRecordMapper.insert(msgRecord);
        return ApiResponse.SUCCESS;
    }

    @GetMapping("/config/query")
    public ApiResponse<MsgConfig> configQuery(@RequestParam("configId") Integer configId) {
        return ApiResponse.success(msgConfigMapper.selectById(configId));
    }

    @GetMapping("/dateFormat")
    public ApiResponse<String> dateFormat(@RequestParam("date") Date date) {
        DateTime dateTime = DateUtil.parse(date.toString(), "yyyy-MM-dd HH:mm:ss");

        return ApiResponse.success(dateTime.toString());
    }

    /**
     * {"type": [1, 2, 3, 4, 5],"content": {"video": ["1.mp4", "2.mp4"],"audio": ["1.mp3", "2.mp3"],"file": ["1.pdf", "2.pdf"],"page": ["www.baidu.com", "www.taobao.com"],"ext": {"headImg": "1.jpg","nickName": "昵称"}},"msgStr": ""}
     *
     * @param msg json字符串
     * @return 处理结果
     */
    @RequestMapping("/push")
    public String push(@RequestParam("msg") String msg) {


        return "admin index";
    }

}
