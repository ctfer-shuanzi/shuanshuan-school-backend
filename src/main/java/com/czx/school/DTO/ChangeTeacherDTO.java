package com.czx.school.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeTeacherDTO {
    private String number;
    private String oldName;
    private String newName;
    private String sex;
    private Integer age;
    private String title;
    private String department;
    private LocalDate hireDate;
}
