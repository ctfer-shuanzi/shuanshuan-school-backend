package com.czx.school.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Course")
public class Course {
    @TableId
    private String id;
    private String name;
    private Double score;
    private String teacher;
}