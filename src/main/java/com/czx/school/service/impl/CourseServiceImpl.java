package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.ChangeCourseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Course;
import com.czx.school.mapper.CourseMapper;
import com.czx.school.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    // 增
    @Override
    public boolean add(Course course) {
        return courseMapper.insert(course) > 0;
    }
    // 删
    @Override
    public boolean delete(String name) {
        return courseMapper.delete(new LambdaQueryWrapper<Course>().eq(Course::getName,name)) > 0;
    }
    // 改
    @Override
    public boolean update(ChangeCourseDTO changeCourseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(changeCourseDTO,course);
        course.setName(changeCourseDTO.getNewName());

        return courseMapper.update(course,new UpdateWrapper<Course>().eq("name",changeCourseDTO.getOldName())) > 0;
    }
    // 查
    @Override
    public Course selectByCid(String cid) {
        return courseMapper.selectByCid(cid);
    }
    @Override
    public Course selectByName(String name){
        return courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getName,name));
    }
    @Override
    public List<Course> selectByTeacher(String teacher) {
        return courseMapper.selectList(new LambdaQueryWrapper<Course>().eq(Course::getTeacher,teacher));
    }
    @Override
    public PageResponse<Course> getPages(PageDTO pageDTO) {
        IPage<Course> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Course> result = courseMapper.selectPage(page,null);

        List<Course> records = result.getRecords();

        PageResponse<Course> pageResponse = new PageResponse<>();
        pageResponse.setList(records);
        pageResponse.setTotal(result.getTotal());
        pageResponse.setCurrent(result.getCurrent());
        pageResponse.setSize(result.getSize());
        pageResponse.setPages(result.getPages());

        return pageResponse;
    }
}