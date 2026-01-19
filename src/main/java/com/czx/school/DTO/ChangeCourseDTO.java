package com.czx.school.DTO;

import lombok.Data;

@Data
public class ChangeCourseDTO {
    private String id;
    private String oldName;
    private String newName;
    private Double score;
    private String teacher;
}