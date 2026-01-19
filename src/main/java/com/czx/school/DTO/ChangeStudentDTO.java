package com.czx.school.DTO;

import lombok.Data;

@Data
public class ChangeStudentDTO {
    private String number;
    private String oldName;
    private String newName;
    private String sex;
    private String age;
    private String major;
}