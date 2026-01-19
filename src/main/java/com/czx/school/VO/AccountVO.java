package com.czx.school.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountVO {
    private String id;
    private String username;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;
    private Integer deleted;
}
