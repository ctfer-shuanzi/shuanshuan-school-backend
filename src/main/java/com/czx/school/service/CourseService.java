package com.czx.school.service;

import com.czx.school.entity.Course;
import com.czx.school.DTO.ChangeScoreDTO;
import com.czx.school.DTO.ChangeTeacherDTO;

public interface CourseService {
    boolean addCourse(Course course);
    boolean deleteByName(String name);
    Course selectByName(String name);
    Double selectScoreByName(String name);
    String selectTeacherByName(String name);
    boolean changeScore(ChangeScoreDTO changeScoreDTO);
    boolean changeTeacher(ChangeTeacherDTO changeTeacherDTO);
}
