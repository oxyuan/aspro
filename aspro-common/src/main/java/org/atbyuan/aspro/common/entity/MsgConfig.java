package org.atbyuan.aspro.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author atbyuan
 * @since 2022/4/18 23:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgConfig implements Serializable {

    /** 主键ID */
    private Integer id;
    /** 配置信息 */
    private String content;

    /** 版本号 */
    private Integer revision;
    /** 创建时间 */
    private Date createTime;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    private Date updateTime;
    /** 更新者 */
    private String updater;

    private static final long serialVersionUID = 1L;

}