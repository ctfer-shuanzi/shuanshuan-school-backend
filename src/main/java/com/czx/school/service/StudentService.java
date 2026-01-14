package com.czx.school.service;

import com.czx.school.entity.Student;
import com.czx.school.DTO.ChangStudentMajorDTO;
import com.czx.school.DTO.ChangeStudentNameDTO;

import java.util.List;

public interface StudentService {
    // StudentMapper中定义的带有@Select注解的方法
    Student selectStudentByNumber(String number);
    // 调用wrapper和mapper方法的方法
    // 增
    boolean addStudent(Student student);
    // 删
    boolean deleteByName(String name);
    // 查
    Student selectStudentByName(String name);
    List<Student> selectStudentsByMajor(String major);
    // 改
    boolean changeName(ChangeStudentNameDTO chaneStudentNameDTO);
    boolean changeMajor(ChangStudentMajorDTO changStudentMajorDTO);
}