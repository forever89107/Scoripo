package com.my.resource.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author MBG
 * @since 2022-10-03 02:27:38
 */
@Getter
@Setter
@TableName("user")
public class OrmUser extends Model<OrmUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * uuid
     */
    private String uuid;

    /**
     * real name
     */
    @TableField("name")
    private String name;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("displayname")
    private String displayname;

    @TableField("birthday")
    private Date birthday;

    /**
     * 0:female，1:male，2:other
     */
    @TableField("gender")
    private Integer gender;

    @TableField("email")
    private String email;

    /**
     * identification_number
     */
    @TableField("national_id_num")
    private String nationalIdNum;

    /**
     * phone numder
     */
    @TableField("phone")
    private String phone;

    @TableField("create_time")
    private Date createTime;

    @TableField("last_modified_time")
    private Date lastModifiedTime;

    @TableField("updater")
    private String updater;


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

}
