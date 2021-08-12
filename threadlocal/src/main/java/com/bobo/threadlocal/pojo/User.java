package com.bobo.threadlocal.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Bobo
 * @date 2021/8/11
 *
 * User实体
 * Entity表需要自动填充的字段使用fill = FieldFill.INSERT或者fill = FieldFill.UPDATE。代表在insert或update时自动填充
 */
@Data
@Entity
@TableName(value = "user")
public class User implements Serializable {

    /**
     * 用户ID
     */
    @Id
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value="name")
    private String name;

    /**
     * 密码
     */
    @TableField(value="password")
    @JsonIgnore
    private String password;

    /** 创建人 */
    @TableField(value="create_by",fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    /** 创建时间 */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 修改者 */
    @TableField(value="update_by" ,fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateBy;

    /** 修改时间 */
    @JsonFormat(timezone = "GMT+8",shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 是否删除(0,否；1,是) */
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;


}
