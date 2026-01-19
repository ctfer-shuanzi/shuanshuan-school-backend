package com.czx.school.service;

import com.czx.school.DTO.ChangeCourseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Course;

import java.util.List;

public interface CourseService {
    // 增
    boolean add(Course course);
    // 删
    boolean delete(String name);
    // 改
    boolean update(ChangeCourseDTO changeCourseDTO);
    // 查
    Course selectByCid(String cid);
    Course selectByName(String name);
    List<Course> selectByTeacher(String teacher);
    PageResponse<Course> getPages(PageDTO pageDTO);
}