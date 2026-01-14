package com.czx.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("account")
public class Account {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;
    private Integer deleted;
}
