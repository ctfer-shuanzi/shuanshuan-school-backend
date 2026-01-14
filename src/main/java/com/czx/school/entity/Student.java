package com.czx.school.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Student")
public class Student {
    @TableId(value = "number")
    private String number;
    private String name;
    private String sex;
    private String age;
    private String major;
}