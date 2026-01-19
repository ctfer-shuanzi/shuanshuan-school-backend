package com.czx.school.service;

import com.czx.school.DTO.ChangeTeacherDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Teacher;

import java.util.List;

public interface TeacherService {
    // 增
    boolean add(Teacher teacher);
    // 删
    boolean delete(String name);
    // 改
    boolean update(ChangeTeacherDTO changeTeacherDTO);
    // 查
    Teacher selectByName(String name);
    List<Teacher> selectByDepartment(String department);
    PageResponse<Teacher> getPages(PageDTO pageDTO);
}
