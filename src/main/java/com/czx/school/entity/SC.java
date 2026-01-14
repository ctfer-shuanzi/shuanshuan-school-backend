package com.czx.school.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("SC")
public class SC {
    @TableId
    private String id;
    private String sno;
    private String cid;
    private String semester;
    private String classTime;
    private Double grade;
}
