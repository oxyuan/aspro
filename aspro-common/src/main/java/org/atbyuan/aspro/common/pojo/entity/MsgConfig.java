package org.atbyuan.aspro.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id",type= IdType.AUTO)
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
