package org.atbyuan.aspro.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 内容记录表
 *
 * @author atbyuan
 * @since 2022-04-18 12:38
 */
// @Table(name = "msg_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgRecord implements Serializable {

    /** 主键ID */
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    /** 配置id */
    private Integer configId;
    /** 消息标题 */
    private String title;
    /** 消息内容 */
    private String content;
    /** 用户ID */
    private String uid;
    /** 姓名 */
    private String nickname;
    /** 透传字段 */
    private String msgStr;

    /** 版本号 */
    private Integer revision;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTF-8")
    private Date createTime;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTF-8")
    private Date updateTime;
    /** 更新者 */
    private String updater;

    private static final long serialVersionUID = 1L;
}