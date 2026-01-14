package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.entity.Course;
import com.czx.school.DTO.ChangeScoreDTO;
import com.czx.school.DTO.ChangeTeacherDTO;
import com.czx.school.mapper.CourseMapper;
import com.czx.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Course selectByName(String name){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, name);
        return courseMapper.selectOne(queryWrapper);
    }
    @Override
    public Double selectScoreByName(String name){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, name);
        return courseMapper.selectOne(queryWrapper).getScore();
    }
    @Override
    public String selectTeacherByName(String name){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, name);
        return courseMapper.selectOne(queryWrapper).getTeacher();
    }
    @Override
    public boolean addCourse(Course course) {
        return courseMapper.insert(course) > 0;
    }
    @Override
    public boolean deleteByName(String name){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, name);
        return courseMapper.delete(queryWrapper) > 0;
    }
    @Override
    public boolean changeScore(ChangeScoreDTO changeScoreDTO){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, changeScoreDTO.getName());

        Course updateCourse = new Course();
        updateCourse.setScore(changeScoreDTO.getScore());

        return courseMapper.update(updateCourse, queryWrapper) > 0;
    }
    @Override
    public boolean changeTeacher(ChangeTeacherDTO changeTeacherDTO){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getName, changeTeacherDTO.getName());

        Course updateCourse = new Course();
        updateCourse.setTeacher(changeTeacherDTO.getNewTeacher());

        return courseMapper.update(updateCourse, queryWrapper) > 0;
    }
}
