package com.czx.school.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("teacher")
public class Teacher {
    @TableId
    private String number;
    private String name;
    private String sex;
    private Integer age;
    private String title;
    private String department;
    private LocalDate hireDate;
}
