package com.czx.school.service;

import com.czx.school.DTO.ChangeStudentDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Student;

import java.util.List;

public interface StudentService {
    // 增
    boolean add(Student student);
    // 删
    boolean delete(String name);
    // 改
    boolean update(ChangeStudentDTO changeStudentDTO);
    // 查
    Student selectByNumber(String number);// StudentMapper中定义的带有@Select注解的方法
    Student selectByName(String name);
    List<Student> selectByMajor(String major);
    PageResponse<Student> getPages(PageDTO pageDTO);
}