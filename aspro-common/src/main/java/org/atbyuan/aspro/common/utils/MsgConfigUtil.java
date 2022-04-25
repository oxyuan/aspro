package org.atbyuan.aspro.common.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.atbyuan.aspro.common.enums.MsgEnums;
import org.atbyuan.aspro.common.pojo.dto.MsgConfigDto;

import java.util.List;

/**
 * @author atbyuan
 * @since 2022/4/25 18:01
 */
@UtilityClass
public class MsgConfigUtil {

    public static List<MsgEnums.Category> convert(String msgConfig) {
        List<MsgEnums.Category> types = Lists.newArrayList();
        if (StringUtils.isBlank(msgConfig) || !JSONUtil.isJson(msgConfig)) {
            return types;
        }
        JSON.parseArray(msgConfig, MsgConfigDto.class)
                .stream()
                .filter(MsgConfigDto::getEnabled)
                .forEach(dto -> types.add(MsgEnums.Category.of(dto.getCode())));
        return types;
    }

}
