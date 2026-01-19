package com.czx.school.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChangeAccountDTO {
    private String oldName;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;
    private Integer deleted;
}